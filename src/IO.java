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
        String configPath = "C:\\Users\\jax\\Desktop\\CSVtest\\csv.csv";
       // new operateCSV().writeConfig(configPath, "ddd", new operateCSV().getPath(), 30);
        new operateCSV().readConfig(configPath);
    }
}

class operateCSV {

    public Path getPath() {
        //以当前路径来创建path对象
        Path getPathForNow = Paths.get(".");
        //获取绝对路径
        System.out.println(getPathForNow.toAbsolutePath());
        return getPathForNow.toAbsolutePath();
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

    //读取配置文件
    public void readConfig(String configPath) {
        File csv = new File(configPath);  // CSV文件路径
        if (csv == null) {
            System.out.println("文件不存在！");
        }
        RandomAccessFile br = null;
        String everyLine = null;
        String line = null;
        try
        {
            br = new  RandomAccessFile(configPath, "r");
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                if (line.charAt(0) == '#') {
                    continue;
                }
                everyLine = line;
                String[] temp = everyLine.split(",");
                //将读出的有效期起始时间转换成日期格式
                //System.out.println(temp[2]);
                String strDate = temp[2];
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date=simpleDateFormat.parse(strDate);
                    Date date1 = new Date();
                    System.out.println(date);
                    long compareToNow = date1.getTime() - date.getTime();
                    compareToNow = compareToNow / 86400000;
                    System.out.println(compareToNow);
                    int expiryDate = Integer.parseInt(temp[3]);
                    //删除过期的文件记录并移动指定的文件
                    if (compareToNow >= expiryDate) {
                        long point = br.getFilePointer();
                        System.out.println("line:" + everyLine);
                        deleteFileContent(configPath, everyLine, point);
                    }
                } catch(ParseException px) {
                    px.printStackTrace();
                }
                System.out.println();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除过期的一行记录
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

    //修改文件的有效期
    public void modifyExpiryDate(String configPath, String filePath, String fileName, long newExpiryDate) {
        try {
            RandomAccessFile raf = new RandomAccessFile(configPath, "rw");
            String line = null;
            while ((line = raf.readLine()) != null) {
                if (line.contains(fileName) && line.contains(filePath)) {
                    long point = raf.getFilePointer();
                    while (raf.read() != ',') {
                        point = point - 2;
                        raf.seek(point);
                    }
                    //System.out.println("modifyExpiryDate:" + (raf.read() - 48));
                    //System.out.println("modifyExpiryDate:" + (raf.read() - 48));
                    String ExpiryDate = String.valueOf(newExpiryDate);
                    raf.writeBytes(ExpiryDate);
                }
            }
            raf.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //写入新的一行
    public void writeConfig(String configPath, String fileName, Path filePath, int expiryDate) {
        File config = new File(configPath);
        try {
            RandomAccessFile raf=new RandomAccessFile(configPath,"rw");
            raf.seek(raf.length());  //将指针移动到文件末尾
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = simpleDateFormat.format(date);
            String content = fileName + "," + filePath + "," + str + "," +expiryDate + "\n";//写入的内容
            raf.writeBytes(content); //字符串末尾需要换行符
            raf.close();//关闭文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//通过path来获取文件路径
