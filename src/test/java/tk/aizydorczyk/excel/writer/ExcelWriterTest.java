package tk.aizydorczyk.excel.writer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.aizydorczyk.excel.api.SpreadSheetWriter;
import tk.aizydorczyk.excel.common.model.BookDto;
import tk.aizydorczyk.excel.data.TestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tk.aizydorczyk.excel.common.utility.ExceptionHelper.getOrRethrowException;

public final class ExcelWriterTest {

	private static final String PATH = "./test.xlsx";
	private static List<BookDto> exampleDtos;
	private final String SHEET_NAME = "test";

	private SpreadSheetWriter excelWriter;

	@Before
	public void init() {
		exampleDtos = TestData.getExampleDtos();
		excelWriter = ExcelWriter.ofPath(Paths.get(PATH));
	}

	@Test
	public void shouldCreateFile() {
		excelWriter.create(exampleDtos, SHEET_NAME);
		assertTrue(Files.exists(Paths.get(PATH)));
	}


	@Test
	@SuppressWarnings("ConstantConditions")
	public void fileShouldContains13Rows() {
		excelWriter.create(exampleDtos, SHEET_NAME);
		Sheet sheet = getSheet();

		int counter = 0;
		for (Row row : sheet) {
			counter++;
		}

		assertEquals(12, counter);
	}

	@Test
	@SuppressWarnings("ConstantConditions")
	public void fileShouldContains47Cells() {
		excelWriter.create(exampleDtos, SHEET_NAME);
		XSSFSheet sheet = getSheet();

		int counter = 0;
		for (Row row : sheet) {
			counter += row.getPhysicalNumberOfCells();
		}

		assertEquals(47, counter);
	}

	private XSSFSheet getSheet() {
		return getOrRethrowException(() -> {
			FileInputStream excelFile = new FileInputStream(new File(PATH));
			return new XSSFWorkbook(excelFile).getSheetAt(0);
		});
	}

	@After
	public void clean() throws IOException {
		Files.delete(Paths.get(PATH));
	}
}
