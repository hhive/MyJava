import java.io.*;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.RandomAccess;

public class IO {
    public static void main(String[] args) {
        new operateCSV().readCsv();
    }
}

class operateCSV {

    public void getPath() {
        //以当前路径来创建path对象
        Path getPathForNow = Paths.get(".");
        //获取绝对路径
        System.out.println(getPathForNow.toAbsolutePath());
    }
    public void operateFile() {
        //move file
//        public static Path move(Path source, Path target, CopyOption... options)
//        throws IOException
//        {
//            FileSystemProvider provider = provider(source);
//            if (provider(target) == provider) {
//                // same provider
//                provider.move(source, target, options);
//            } else {
//                // different providers
//                CopyMoveHelper.moveToForeignTarget(source, target, options);
//            }
//            return target;
//        }

    }
    public void readCsv() {
        String path = "C:\\Users\\jax\\Desktop\\CSVtest\\csv.csv";
        File csv = new File(path);  // CSV文件路径
        if (csv == null) {
            System.out.println("文件不存在！");
        }
        RandomAccessFile br = null;
        try
        {
            br = new  RandomAccessFile(path, "r");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
       // modifyExpiryDate(path, "bbb", "C:\\Users\\jax\\Desktop\\CSVtest", 20);
        try {
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                if (line.charAt(0) == '#') {
                    continue;
                }
                everyLine = line;
                String[] temp = everyLine.split(",");
                //System.out.println(temp[2]);
                String strDate = temp[2];
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date=simpleDateFormat.parse(strDate);
                    Date date1 = new Date();
                    System.out.println(date);
                    long compareToNow = date1.getTime() - date.getTime();
                    compareToNow = compareToNow / 86400000;
                    System.out.println(compareToNow);
                    int expiryDate = Integer.parseInt(temp[3]);

                    //删除过期的文件记录
                    if (compareToNow >= expiryDate) {
                        long point = br.getFilePointer();
                        System.out.println("line:" + everyLine);
                        deleteFileContent(path, everyLine, point);
                    }
                } catch(ParseException px) {
                    px.printStackTrace();
                }
                System.out.println();
            }
           // br.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private boolean deleteFileContent(String path, String oldstr, long point) {
        System.out.println("modifyFileContent");
        try {
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            int length = oldstr.length();
            point = point - length - 1;
            while (length > 0) {
                raf.seek(point);
                raf.writeBytes("#");
                point++;
                length--;
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void modifyExpiryDate(String parentFilePath, String filePath, String fileName, long newExpiryDate) {
        try {
            RandomAccessFile raf = new RandomAccessFile(parentFilePath, "rw");
            String line = null;
            while ((line = raf.readLine()) != null) {
                if (line.contains(fileName) && line.contains(filePath)) {
                    long point = raf.getFilePointer();
                    while (raf.read() != ',') {
                        point = point - 2;
                        raf.seek(point);
                    }
                    String ExpiryDate = String.valueOf(newExpiryDate);
                    raf.writeBytes(ExpiryDate);
                }
            }
            raf.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}


//通过path来获取文件路径
