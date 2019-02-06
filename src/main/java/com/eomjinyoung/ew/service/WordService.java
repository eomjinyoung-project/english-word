package com.eomjinyoung.ew.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.WordDao;
import com.eomjinyoung.ew.domain.Word;

@Service
public class WordService {
  
  WordDao wordDao;
  
  public WordService(WordDao wordDao) {
    this.wordDao = wordDao;
  }
  
  public void uploadFromExcelToDatabase(File file) throws Exception {
    Workbook wb = WorkbookFactory.create(file);
    //DataFormatter formatter = new DataFormatter();
    Sheet sheet1 = wb.getSheetAt(0);
    int count = 0;
    for (Row row : sheet1) {
      
      Word word = new Word(
          row.getCell(0).getStringCellValue(), 
          row.getCell(1).getStringCellValue());
      
      try {
        wordDao.insert(word);
        
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("wordNo", word.getNo());
        paramMap.put("wordGroupNo", row.getCell(2).getNumericCellValue());
        
        wordDao.insertInGroupByWordNo(paramMap);
        
        System.out.println(++count);
        
      } catch (Exception e) {
        System.out.printf("%s=%s\n", row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
        System.out.println(e.toString() + ":" + e.getMessage());
        
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("wordName", word.getName());
        paramMap.put("wordGroupNo", row.getCell(2).getNumericCellValue());
        
        wordDao.insertInGroupByWordName(paramMap);
      }
      /*
        for (Cell cell : row) {
            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
            System.out.print(cellRef.formatAsString());
            System.out.print(" - ");

            // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
            String text = formatter.formatCellValue(cell);
            System.out.println(text);

            // Alternatively, get the value and format it yourself
            switch (cell.getCellType()) {
                case STRING:
                    System.out.println(cell.getRichStringCellValue().getString());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        System.out.println(cell.getDateCellValue());
                    } else {
                        System.out.println(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    System.out.println(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    System.out.println(cell.getCellFormula());
                    break;
                case BLANK:
                    System.out.println();
                    break;
                default:
                    System.out.println();
            }
        }
    */
    }
  }
  
  public List<Word> getWords(String name) {
    return wordDao.findByName(name);
  }
  
  public int addWord(Word word) {
    return wordDao.insert(word);
  }
}
