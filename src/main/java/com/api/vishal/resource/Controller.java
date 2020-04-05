package com.api.vishal.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.api.vishal.exception.*;
import com.api.vishal.model.Vishal;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.vishal.repository.VishalRepository;
@RestController
public class Controller {

	
	
@Autowired
private VishalRepository repository;


// Below Line of code is Used to save the data resource using (/save)

@PostMapping("/save")
@ResponseStatus(HttpStatus.CREATED)
public String save(@RequestBody Vishal vishal )
{
  Optional<Vishal> user=repository.findById(vishal.getId());
  if(user.isPresent())
  {
	  
	  return " Record Already Exist With Id ::"+vishal.getId();
	  
  }
	repository.save(vishal);
	return "New Record is Added Successfull With Id is::"+ vishal.getId();

	
}
@GetMapping("/findAll")
public List<Vishal> findAll()
{
	List<Vishal>lst=repository.findAll();
	return lst;
	
		

}
/*@GetMapping("/findby/{id}")
public Optional<Book> getBook(@PathVariable int id)
{
	
	return repository.findById(id);
		

}*/
@DeleteMapping("/delete/{id}")
public String deletedetails(@PathVariable String id)
{
	Optional<Vishal>user = repository.findById(id);
	if(!user.isPresent())
	{
		throw new ResourceNotFoundException("Hello Vishal !! Resource Not Found with Given Id");  
		
		
	}
	repository.deleteById((id));
	return "The Document has been Deleted with Id::"+id;	
	
	
}
@GetMapping("/findByName/{name}")
public List<Vishal> getdetails(@PathVariable String name)
{
	List<Vishal>user=repository.findByName(name);
	if(user.isEmpty())  
		throw new ResourceNotFoundException("Hello Vishal !! Resource Not Found with Given Name:"+ name);  
	 return user;
}
}
