package com.datacenter.core.utils.excel;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


public class POIPerformanceExcel2007 extends DefaultHandler {

	private Map<Integer, String[]> excelData = new LinkedHashMap<Integer, String[]>();
	/**
	 * 共享字符串表
	 */
	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;
	private int sheetIndex = -1;
	String[] curColData =null;
	private int curRow = 0; //当前行
	private int curCol = 0; //当前列
	private int columnNum=0;
	/**
	 * 读取所有工作簿的入口方法
	 * @param path
	 * @throws Exception
	 */
	public void process(String path) throws Exception {
		try{
			OPCPackage pkg = OPCPackage.open(path);
			XSSFReader r = new XSSFReader(pkg);
			SharedStringsTable sst = r.getSharedStringsTable();
			XMLReader parser = fetchSheetParser(sst);
			Iterator<InputStream> sheets = r.getSheetsData();
			while (sheets.hasNext()) {
				curRow = 0;
				sheetIndex++;
				InputStream sheet = sheets.next();
				InputSource sheetSource = new InputSource(sheet);
				parser.parse(sheetSource);
				sheet.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
	
	}

	/**
	 * 该方法自动被调用，每读一行调用一次，在方法中写自己的业务逻辑即可
	 * @param sheetIndex 工作簿序号
	 * @param curRow 处理到第几行
	 * @param rowList 当前数据行的数据集合
	 */
	public void optRow(int sheetIndex, int curRow, String[] curColData) {
		if (curRow >= 1) { // 不要表头
			excelData.put(curRow, (curColData));
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		// c => 单元格
	
		if (name.equals("c")) {
			curCol = StringUtils.convertLetterToNum(attributes.getValue("r"));
		
			// 如果下一个元素是 SST 的索引，则将nextIsString标记为true
			String cellType = attributes.getValue("t");
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		// 置空
		lastContents = "";
	}

	public void endElement(String uri, String localName, String name) throws SAXException {
		// 根据SST的索引值的到单元格的真正要存储的字符串
		// 这时characters()方法可能会被调用多次
		if (nextIsString) {
			try {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			} catch (Exception e) {
			}
		}
		// v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
		// 将单元格内容加入rowlist中，在之前先去掉字符串前后的空白符
		
			if (name.equals("v")) {
				if(curCol<=this.columnNum){ //保证只要的列
					String value = lastContents.trim();
					value = value.equals("") ? "0" : value;
					curColData[curCol-1] = value; //数组下标从0开始
				}
			} else {
				// 如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
				if (name.equals("row")) {
					optRow(sheetIndex, curRow, curColData);
					init();
					curRow++;
					curCol = 0;
				}
			}
		}
		
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		// 得到单元格内容的值
		lastContents += new String(ch, start, length);
	}

	/**
	 * 读取第一个工作簿的入口方法
	 * @param path
	 */
	public void readExcel2007OneSheet(String path, int sheetNum, int columnNum) {
		this.columnNum = columnNum;
		try{
			OPCPackage pkg = OPCPackage.open(path);
			init();
			XSSFReader r = new XSSFReader(pkg);
			SharedStringsTable sst = r.getSharedStringsTable();
			XMLReader parser = fetchSheetParser(sst);
			Iterator<InputStream> sheets = r.getSheetsData();
			InputStream sheet = null;
			for (int i = 0; i <= sheetNum; i++) {
				sheet = sheets.next();
			}
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
			pkg=null;
			r=null;
			sst=null;
			sheet=null;
			sheetSource =null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**   
	 * 方法名称:init
	 * TODO(初始化列长)      
	 * @param columnNum
	 */
	private void init(){
		this.curColData = new String[columnNum];
		for (int i = 0; i < curColData.length; i++) {
			curColData[i]="0";
		}
	}

	public Map<Integer, String[]> getExcelData() {
		return this.excelData;
	}

	

	public static void main(String[] args) throws Exception {

		POIPerformanceExcel2007 pOIExcel2007 = new POIPerformanceExcel2007();
		long a = System.currentTimeMillis();
		pOIExcel2007.readExcel2007OneSheet("E:/workspace/y_project/net-game/ExcelConfigTables/maintask.xlsx", 1, 1);
		System.out.println(System.currentTimeMillis() - a);
	}

}
