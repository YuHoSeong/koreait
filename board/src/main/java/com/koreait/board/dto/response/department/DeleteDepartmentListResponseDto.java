package com.koreait.board.dto.response.department;

import java.util.ArrayList;
import java.util.List;

import com.koreait.board.entity.DepartmentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDepartmentListResponseDto {
	//? 부서코드
	private String departmentCode;
	//? 부서명
	private String name;
	//? 부서장 사번
	private int chief;
	//? 부서 연락처
	private String telNumber;

	public DeleteDepartmentListResponseDto(DepartmentEntity departmentEntity){
		this.departmentCode = departmentEntity.getDepartmentCode();
		this.name = departmentEntity.getName();
		this.chief = departmentEntity.getChief();
		this.telNumber = departmentEntity.getTelNumber();
	}

	public static List<DeleteDepartmentListResponseDto> copyList(List<DepartmentEntity> departmentEntities){
		List<DeleteDepartmentListResponseDto> result = new ArrayList<DeleteDepartmentListResponseDto>();
		
		for(DepartmentEntity departmentEntity: departmentEntities){
			DeleteDepartmentListResponseDto item = new DeleteDepartmentListResponseDto(departmentEntity);
			result.add(item);
				
		}

		return result;
	}
}
