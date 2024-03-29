package com.ssafy.enjoytrip.plan.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanSeqDto;
import com.ssafy.enjoytrip.plan.model.service.PlanService;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
public class PlanController {
	private final Logger logger = LoggerFactory.getLogger(PlanController.class);

	private PlanService planService;

	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> write(@RequestBody PlanDto planDto) {
		logger.debug("write planDto : {}", planDto);
		
		try {
			planService.registPlan(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/{planNo}")
	public ResponseEntity<String> update(@RequestBody PlanDto planDto) {
		logger.debug("modify planDto : {}", planDto);
		
		try {
			planService.modifyPlan(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{planNo}")
	public ResponseEntity<String> delete(@PathVariable int planNo) {
		logger.debug("delete planNo : {}", planNo);
		
		try {
			planService.deletePlan(planNo);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}

	@GetMapping("/list/{userId}/{word}")
	public ResponseEntity<?> listByTitle(@PathVariable String userId, @PathVariable String word) {
		logger.debug("search by title : {}", word);
		HashMap<String, String> map = new HashMap<>();
		map.put("word", word);
		map.put("userId", userId);

		try {
			List<PlanDto> plans = planService.searchListByTitle(map);
			if (plans.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<List<PlanDto>>(plans, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list/{userId}")
	public ResponseEntity<?> list(@PathVariable String userId) {
		logger.debug("list all plans userId : {}", userId);

		try {
			List<PlanDto> plans = planService.listPlan(userId);
			if (plans.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<List<PlanDto>>(plans, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/view/{planNo}")
	public ResponseEntity<?> view(@PathVariable int planNo) {
		logger.debug("view plan : {}", planNo);
		
		try {
			PlanDto plan = planService.getPlan(planNo);
			if (plan == null) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<PlanDto>(plan, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping("/seq/{planNo}")
	public ResponseEntity<?> getPlanSeqs(@PathVariable int planNo) {
		logger.debug("getPlanSeqs : {}", planNo);
		
		try {
			List<PlanSeqDto> seq = planService.getPlanSeqs(planNo);
//			System.out.println("seq list : "+seq);
			if (seq.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<List<PlanSeqDto>>(seq, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
    	HttpHeaders resHeaders = new HttpHeaders();
    	resHeaders.add("Content-Type", "application/json;charset=UTF-8");
    	return new ResponseEntity<String>("errors : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
