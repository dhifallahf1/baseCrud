package com.fd.baseCrud;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @PutMapping("/{id}")
    public HttpEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee emp) throws InterruptedException {
        Optional<Employee> employeeSearch = employeeService.getEmployee(id);
        if(!employeeSearch.isPresent() ){
            return ResponseEntity.notFound().build();
        }else {
            // test commit
           // employeeSearch.get().setDateNaiss(emp.getDateNaiss());
            return ResponseEntity.ok(employeeService.update(emp));
       }
    }
    @GetMapping(value = "/")
    public HttpEntity<List<Employee>> getEmployees(@RequestHeader Map<String,String> accept,HttpServletRequest request){
        // response  = new HttpEntity<Employee>();
       // accept.forEach((headkey,headval)-> System.out.println(headkey +" "+headval));


        return new HttpEntity<List<Employee>>(employeeService.getEmployees(),new HttpHeaders());
    }
    @PostMapping("/")
    public Employee save(@RequestBody Employee employee )  {
        System.out.println("employee from post "+employee);
       return employeeService.save(employee);
    }

    @PostMapping("/form")
    public ResponseEntity<Employee> saveForm(@RequestBody Employee employee )  {
        RequestEntity<Employee> request =  new RequestEntity<>(employee,HttpMethod.POST,URI.create("http://localhost:8081/employee/"));
        RestTemplate template = new RestTemplate();
        //Employee emp = template.exchange(request,Employee.class).getBody();

        return template.getForEntity( request.getUrl(),Employee.class);
    }

    @GetMapping(value = "/get/{id}")
    @JsonView(Employee.EnableView.class)
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id, HttpServletRequest request)  {
        Optional<Employee> employeeSearch = employeeService.getEmployee(id);
        URI uri  = ServletUriComponentsBuilder.fromRequest(request).build().toUri();
        System.out.println(uri);
        if(!employeeSearch.isPresent() ){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           return  ResponseEntity.notFound().build();

        }else {
           return new ResponseEntity<Employee>(employeeSearch.get(), new HttpHeaders(),HttpStatus.OK);
            //return ResponseEntity.ok(Mono.justOrEmpty(employeeSearch.get()));
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));*/
        binder.setAllowedFields("dateNaiss");
    }
}
