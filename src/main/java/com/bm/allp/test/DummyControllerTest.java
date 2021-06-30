package com.bm.allp.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bm.allp.model.RoleType;
import com.bm.allp.model.User;
import com.bm.allp.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	// 의존성 주입 (bean 등록)
	@Autowired
	private UserRepository userRepository;
	
	/////////////////////////////////////////////////////// SELECT ///////////////////////////////////////////////////////
	
	// findById : 한 번에 하나의 데이터를 가져옴
	// 주소의 {id} 값으로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/allp/dummy/select/{id}
	@GetMapping("/dummy/select/{id}")
	public User userSelect(@PathVariable int id) {
		
		// .orElseThrow 부터는 해당 id 값이 null일때를 대비하는 코드다 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			public IllegalArgumentException get() {
				return new IllegalArgumentException("userDetail EXCEPTION");
			}
		});
		
		return user;
	}
	
	// findAll : 한 번에 여러개의 데이터를 가져옴
	// http://localhost:8000/allp/dummy/select/all
	@GetMapping("/dummy/select/all")
	public List<User> userSelectAll(){
		return userRepository.findAll();
	}
	
	// findAll(pageable) : 한 페이지당 n건의 데이터를 가져옴
	// size = 2 (두 개씩)
	// sort = "id" (id로 정렬)
	// direction = Sort.Direction.DESC (최근 순서대로)
	// http://localhost:8000/allp/dummy/select/some		  (첫 번째 페이지)
	// http://localhost:8000/allp/dummy/select/some?page=0 (첫 번째 페이지)
	// http://localhost:8000/allp/dummy/select/some?page=1 (두 번째 페이지)
	// http://localhost:8000/allp/dummy/select/some?page=2 (세 번째 페이지)
	// http://localhost:8000/allp/dummy/select/some?page=3 (네 번째 페이지)
	//						   .
	//						   .
	//						   .
	//						   .
	@GetMapping("/dummy/select/some")
	public List<User> userSelectSome(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> userPaging = userRepository.findAll(pageable); // 페이징 객체 생성
		
		List<User> users = userPaging.getContent(); // 페이직 객체 가공 // Content만 빼오기
		
		return users;
	}
	
	/////////////////////////////////////////////////////// INSERT ///////////////////////////////////////////////////////
	
	// http://localhost:8000/allp/dummy/insert/value (요청)
	// http의 body에서 각각의 데이터를 개별적으로 받아와서 요청하는 메서드
	@PostMapping("/dummy/insert/value")
	public String insertValue(String username, String password, String email) {
		
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		
		return "joinValue 완료";
	}
	
	// http://localhost:8000/allp/dummy/insert/field (요청)
	// http의 body에서 User 클래스의 필드들을 받아와 User 객체를 만들어서 요청하여 DB에 넣는 일까지 하는 메서드
	@PostMapping("/dummy/insert/field")
	public String insertField(User user) {
		
		System.out.println("****************************** 입력받은 회원정보 ******************************");
		System.out.println("id : " + user.getId()); // 따로 안받는 값
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole()); // Enum으로 받는 값
		System.out.println("createDate : " + user.getCreateDate()); // 따로 안받는 값
		System.out.println("**************************************************************************");
		System.out.println();
		
		System.out.println("******************************** Role 등록 ********************************");
		user.setRole(RoleType.USER);
		System.out.println("**************************************************************************");
		System.out.println();
		
		System.out.println("***************************** DB에 회원정보 등록 *****************************");
		userRepository.save(user);
		System.out.println("**************************************************************************");
		System.out.println();
		
		System.out.println("****************************** 저장된 회원정보 *******************************");
		System.out.println("id : " + user.getId()); // 따로 안받는 값
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole()); // Enum으로 받는 값
		System.out.println("createDate : " + user.getCreateDate()); // 따로 안받는 값
		System.out.println("*************************************************************************");
		
		return "joinField 완료";
	}
	
	/////////////////////////////////////////////////////// UPDATE ///////////////////////////////////////////////////////
	
	// email, password 수정
	// http://localhost:8000/allp/dummy/update/{id}
	@Transactional // 함수 종료시에 영속화 되어있는 데이터의 변경을 자동으로 감지하여 DB에 반영한다 (flush)
	@PutMapping("/dummy/update/{id}")
	public User update(@PathVariable int id, @RequestBody User requestUser) {
		
		System.out.println("-------------------수정하기 위해 전달받은 값-------------------");
		System.out.println("id : " + id);							// 주소값에서 받아옴 @PathVariable
		System.out.println("password : " + requestUser.getPassword());		// body에서 받아옴 @RequestBody
		System.out.println("email : " + requestUser.getEmail());			// body에서 받아옴 @RequestBody
		System.out.println();
		
		System.out.println("--------------------------영속화--------------------------");
		// 해당 id의 회원정보를 가져옴 (영속화)
		// findById (람다식)
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("userUpdate EXCEPTION");
		});
		System.out.println();
		
		// 가져온 회원정보의 password와 email을 변경한다 (아직 DB에 등록된것은 아님)
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// save가 필요없는 이유는 @Transactional을 통해 더티체킹이 가능하기 때문이다
		// @Transaction을 지우고 아래의 save로 회원수정을 한다해도 password와 email의 값만 받아왔기 때문에 나머지 컬럼이 null이 된다
		// userRepository.save(userDetail);
		
		System.out.println("이제 이 함수가 종료되면서 @Transactional에 의해 변경을 감지하여 회원정보가 수정된다");
		System.out.println("콘솔창에서 종료후에 바로 작성된 update SQL문을 볼 수 있다");
		System.out.println();
		
		System.out.println("---------------------------종료---------------------------");
		System.out.println();
		
		System.out.println("-----------------------UPDATE문 시작-----------------------");
		
		return user;
	}
	
	/////////////////////////////////////////////////////// DELETE ///////////////////////////////////////////////////////
	
	// http://localhost:8000/allp/dummy/delete/{id}
	@DeleteMapping("/dummy/delete/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "userDelete EXCEPTION";
		}
		
		return "id가 " + id + "인 회원이 성공적으로 삭제되었습니다";
	}

}
