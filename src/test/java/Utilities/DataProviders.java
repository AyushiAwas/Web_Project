package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

String path = System.getProperty("user.dir")+"//testData//LoginDetails.xlsx";
Xlsutility xlsu = new Xlsutility(path);

    @DataProvider(name = "data")
    public String[][] getAllData() throws IOException
    {
        int rowCount = xlsu.getRowCount("Sheet1");
        int colCount = xlsu.getCellCount("Sheet1",1);
        String[][] datapro = new String[rowCount][colCount];
        for(int i =1;i<=rowCount;i++)
        {
            for(int j=0;j<colCount;j++)
            {
                datapro[i-1][j] = xlsu.getCellData("Sheet1",i,j);
            }
        }
        return  datapro;
    }


}
