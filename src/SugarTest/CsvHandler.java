package SugarTest;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CsvHandler 
{
	private String csv;
	
	public CsvHandler(String fileName)
	{
		csv = "data.csv";
	}
	
	public void createCsvFile() throws Exception
	{
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		String[] header = "Field Name,Strategy,Path,Value,RecordView".split(",");
		writer.writeNext(header);
		writer.close();
	}
	
	public void writeCsvFile(Account acc) throws Exception
	{
		CSVWriter writer = new CSVWriter(new FileWriter(csv,true));
		String[] record = {acc.field_name,acc.strategy,acc.path,acc.value,acc.record_view};
		writer.writeNext(record);
		writer.close();
	}
	
	public void createResultFile() throws Exception
	{
		CSVWriter writer = new CSVWriter(new FileWriter("result.csv"));
		String[] header = "Field Name,Expected Result, Actual Result".split(",");
		writer.writeNext(header);
		writer.close();
	}
	
	public void writeResults(String fieldName, String expected, String actual) throws Exception
	{
		CSVWriter writer = new CSVWriter(new FileWriter("result.csv", true));
		String[] result = {fieldName, expected, actual};
		writer.writeNext(result);
		writer.close();
	}
	
	public ArrayList readCsvFile(int startIndex) throws Exception
	{
		CSVReader reader = new CSVReader(new FileReader("data.csv"), ',' , '"' , 1);
		ArrayList<String[]> allElements = (ArrayList<String[]>)reader.readAll();
//		allElements.remove(3);
	reader.close();
		
		return allElements;
	}
}
