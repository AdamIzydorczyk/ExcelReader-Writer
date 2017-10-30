package tk.aizydorczyk.excel.common.model;

import lombok.Builder;
import lombok.Data;
import tk.aizydorczyk.excel.common.annotation.ExcelColumn;
import tk.aizydorczyk.excel.common.annotation.ExcelGroup;

@ExcelGroup(header = "Lender")
@Data
@Builder
public class LenderDto {
	@ExcelColumn(header = "LENDER_ID")
	private Long id;
	@ExcelColumn(header = "LENDER_FIRST_NAME")
	private String firstName;
	@ExcelColumn(header = "LENDER_LAST_NAME")
	private String lastName;
}