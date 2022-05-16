package com.example.company.service.impl;

import com.example.company.bo.EmployeeBO;
import com.example.company.commom.BusinessException;
import com.example.company.commom.BusinessExceptionEnum;
import com.example.company.commom.EmployeeSpecification;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.dto.response.PageResponse;
import com.example.company.entity.Departament;
import com.example.company.entity.Employee;
import com.example.company.repository.IDepartamentRepository;
import com.example.company.repository.IEmployeeRepository;
import com.example.company.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    IDepartamentRepository departamentRepository;

    @Autowired
    private EmployeeBO employeeBO;

    @Override
    public EmployeeResponse findEmployeeById(Long employeeId){

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(employee.isPresent()){
            return employeeBO.convertResponseFromEntity(employee.get());
        }else{
            throw new BusinessException(BusinessExceptionEnum.EMPLOYEES_NOT_FOUND);
        }
    }

    @Override
    public PageResponse<EmployeeResponse> findEmployeesByParameter(String parameterType, String parameterValue, int page, int limit){

        EmployeeSpecification es = new EmployeeSpecification(parameterType, parameterValue);
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Employee> employeePage = employeeRepository.findAll(es, pageRequest);

        if(employeePage.getContent() != null && employeePage.getContent().size() > 0){

            PageResponse<EmployeeResponse> pageResponse = new PageResponse<EmployeeResponse>();
            pageResponse.setCurrentPage(employeePage.getPageable().getPageNumber());
            pageResponse.setPageTotalRecords(employeePage.getContent().size());
            pageResponse.setTotalRecords((int) employeePage.getTotalElements());
            pageResponse.setTotalPages(employeePage.getTotalPages());
            pageResponse.setContent(employeeBO.convertListResponseFromListEntity(employeePage.getContent()));
            return pageResponse;
        }else{
            throw new BusinessException(BusinessExceptionEnum.EMPLOYEES_NOT_FOUND);
        }
    }

    @Override
    public EmployeeResponse saveEmployee(Employee employee){

        String messageError = validateValues(employee);

        if(messageError == ""){
            //Check if already anyone registered with the same document type and document number
            Optional<Employee> savedEmployee = employeeRepository.findDocument(employee.getDocumentType(), employee.getDocumentNumber());

            if(!savedEmployee.isPresent()){

                employee.setEmail(createEmailLayout(employee, Optional.empty()));

                Departament departament = departamentRepository.getById(employee.getDepartament().getDepartamentId());

                if(departament != null && !departament.getName().equals("")){
                    employee.getDepartament().setName(departament.getName());
                }else{
                    throw new BusinessException(BusinessExceptionEnum.DEPARTAMENT_DOESNT_EXIST);
                }

                return employeeBO.convertResponseFromEntity(employeeRepository.save(employee));
            }else{
                throw new BusinessException(BusinessExceptionEnum.DOCUMENT_EXISTS);
            }
        } else {
            throw new BusinessException(messageError);
        }
    }

    @Override
    public EmployeeResponse updateEmployee(Employee employee){

        String messageError = validateValues(employee);

        if(messageError == ""){
            Optional<Employee> savedEmployee = employeeRepository.findById(employee.getEmployeeId());

            if(savedEmployee.isPresent()){
                employee.setEmail(createEmailLayout(employee, savedEmployee));
                employee.setEntryDate(savedEmployee.get().getEntryDate());
                employee.setStatus(savedEmployee.get().getStatus());
                employee.setInsertDate(savedEmployee.get().getInsertDate());

                Departament departament = departamentRepository.getById(employee.getDepartament().getDepartamentId());

                if(departament != null && !departament.getName().equals("")){
                    employee.getDepartament().setName(departament.getName());
                }else{
                    throw new BusinessException(BusinessExceptionEnum.DEPARTAMENT_DOESNT_EXIST);
                }

                return employeeBO.convertResponseFromEntity(employeeRepository.save(employee));
            }else{
                throw new BusinessException(BusinessExceptionEnum.EMPLOYEE_NOT_FOUND);
            }
        }else{
            throw new BusinessException(messageError);
        }
    }

    @Override
    public String deleteEmployee(Long employeeId) {

        Optional<Employee> savedEmployee = employeeRepository.findById(employeeId);

        if(savedEmployee.isPresent()){
            employeeRepository.deleteById(employeeId);
            return "The employee with e-mail: " + savedEmployee.get().getEmail() + " has been deleted.";
        }else{
            throw new BusinessException(BusinessExceptionEnum.EMPLOYEE_NOT_FOUND);
        }
    }

    private String createEmailLayout(Employee employee, Optional<Employee> savedEmployee){
        String createdEmail = "";
        //E-mail layout FirstName + FirstLastName + . + ID + @cidenet.com. + us/co
        //The first e-mail registered doesn't have ID
        String emailHeader = employee.getFirstName().toLowerCase().trim().replaceAll(" ", "") + "." + employee.getFirstLastName().toLowerCase().trim().replaceAll(" ", "");

        if(employee.getJobCountry().equals("ESTADOS UNIDOS")){
            createdEmail = emailHeader+"@cidenet.com.us";
        }else{
            createdEmail = emailHeader+"@cidenet.com.co";
        }

        //If is an update and the created email is the same, desconsider the rule of count the exists emails
        //If isn't an update apply the rule of counts
        if(savedEmployee.isPresent()){
            if(!savedEmployee.get().getEmail().equals(createdEmail)){
                //Check if the e-mail header is already registered
                int countEmailRegistered = employeeRepository.countEmailRegistered(emailHeader);
                //If exists, get the count to increment ID and replace e-mail
                if(countEmailRegistered > 0){
                    createdEmail = employee.getEmail().replace("@", "."+ countEmailRegistered +"@");
                }
            }
        }else{
            //Check if the e-mail header is already registered
            int countEmailRegistered = employeeRepository.countEmailRegistered(emailHeader);
            //If exists, get the count to increment ID and replace e-mail
            if(countEmailRegistered > 0){
                createdEmail = createdEmail.replace("@", "."+ countEmailRegistered +"@");
            }
        }
        if(createdEmail.length() > 300){
            throw new BusinessException(BusinessExceptionEnum.EMAIL_MAX_LENGHT);
        }else{
            return createdEmail;
        }
    }

    private String validateValues(Employee employee){

        boolean isValidated = true;
        String messageError = "The following fields contain the following errors: \n";

        if(employee.getFirstName().equals("")){
            messageError += "First name can't be empty \n";
            isValidated = false;
        }else if(employee.getFirstName().length() > 20){
            messageError += "First name max allowed size: 20 \n";
            isValidated = false;
        }

        if(employee.getMiddleName().length() > 50){
            messageError += "Middle name max allowed size: 50 \n";
            isValidated = false;
        }

        if(employee.getFirstLastName().equals("")){
            messageError += "First last name can't be empty \n";
            isValidated = false;
        }else if(employee.getFirstLastName().length() > 20){
            messageError += "First last name max allowed size: 20 \n";
            isValidated = false;
        }

        if(employee.getSecondLastName().equals("")){
            messageError += "Second last name can't be empty \n";
            isValidated = false;
        }else if(employee.getSecondLastName().length() > 20){
            messageError += "Second last name max allowed size: 20 \n";
            isValidated = false;
        }

        if(employee.getJobCountry().equals("")){
            messageError += "Job country can't be empty \n";
            isValidated = false;
        }else if(!employee.getJobCountry().equals("ESTADOS UNIDOS") && !employee.getJobCountry().equals("COLOMBIA")){
            messageError += "Job country allowed: ESTADOS UNIDOS OR COLOMBIA \n";
            isValidated = false;
        }

        if(employee.getDocumentNumber().equals("")){
            messageError += "Document number can't be empty \n";
            isValidated = false;
        }else if(employee.getDocumentNumber().length() > 20){
            messageError += "Document number max allowed size: 20 \n";
            isValidated = false;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date oneMonthAgo = cal.getTime();

        if(employee.getEntryDate() == null){
            messageError += "The entry date can't be empty \n";
            isValidated = false;
        }else if(employee.getEntryDate().after(new Date())){
            messageError += "The entry date can't be later than today \n";
            isValidated = false;

        }else if(employee.getEntryDate().before(oneMonthAgo)){
            messageError += "The entry date can't be before one month ago \n";
            isValidated = false;
        }

        if(isValidated){
            return "";
        }else {
            return messageError;
        }
    }
}
