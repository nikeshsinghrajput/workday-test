package com.nk.sailpoint.util;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.Page;

import com.nk.sailpoint.entity.Employee;
import com.nk.sailpoint.entity.PageableResponse;



public class Helper {

    //We get Page of type User amd we need to return Page of type DTO
	public static PageableResponse<Employee> getPageableResponse(Page<Employee> page) {
        List<Employee> employees = page.getContent();

        PageableResponse<Employee> response = new PageableResponse<>();
        response.setContent(employees);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

//	public static PageableResponse<Employee> getPageableResponse(Page<Employee> employeePage, Class<Employee> type) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
