package cn.tjucic.selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.regex.Pattern;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.Count;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.asm.Advice.This;

@RunWith(Parameterized.class)
public class TestStudent2 {
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String[] currentInfo ;

	public TestStudent2(String[] info) {
		this.currentInfo = info;
	}
	
	public static List<Object[]> read() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\软件测试名单.xlsx";
		System.out.println(filePath);
		File file = new File(filePath);
		List<Object[]> result = new ArrayList<Object[]>();
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		for(int rowIndex=2; rowIndex<=sheet.getPhysicalNumberOfRows(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if(row != null) {
				Object[][] infoList = new String[1][] ;
				String[]  array  = new String[4];
				//id
				array[0] = String.valueOf((long)row.getCell(1).getNumericCellValue());
				//name
				array[1] = row.getCell(2).getStringCellValue();
				//github
				array[2] = row.getCell(3).getStringCellValue();
				//password
				array[3] = array[0].substring(4);
				infoList[0] = array;
				result.add(infoList);
			}
		}
		System.out.println("read");
		return result;
	}
	
    @Parameterized.Parameters
    public static Collection<Object[]> getData() throws Exception {
		String driverPath = System.getProperty("user.dir") + "\\src\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800";
		driver.manage().timeouts().implicitlyWait(900, TimeUnit.SECONDS);
		List<Object[]> list = read();
        return list;
    }
    
	@Test
	public void testBaidu() throws Exception {
		driver.get(baseUrl);
		// 输入内容，并确定
		driver.findElement(By.name("id")).clear();
		driver.findElement(By.name("id")).sendKeys(currentInfo[0]);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(currentInfo[3]);
		driver.findElement(By.id("btn_login")).sendKeys(Keys.ENTER);
		// 测试信息
		assertEquals(currentInfo[0], driver.findElement(By.id("student-id")).getText());
		assertEquals(currentInfo[1], driver.findElement(By.id("student-name")).getText());
		assertEquals(currentInfo[2], driver.findElement(By.id("student-git")).getText());
		// 返回
		driver.findElement(By.id("btn_logout")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("btn_return")).sendKeys(Keys.ENTER);
	}

}
