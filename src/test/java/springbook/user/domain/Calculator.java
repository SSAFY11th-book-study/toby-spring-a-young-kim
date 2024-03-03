package springbook.user.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Calculator{
    public Integer calcSum(String filePath) throws IOException{
       BufferedReaderCallback sumCallback =
               new BufferedReaderCallback() {
                   @Override
                   public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                       Integer sum = 0;
                       String line = null;
                       while((line = br.readLine()) != null){
                           sum += Integer.valueOf(line);
                       }
                       return sum;
                   }
               };
        return fileReadTemplate(filePath, sumCallback);

    }
    public Integer calcMultiply(String filePath) throws IOException {
        BufferedReaderCallback multitlyCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        Integer multipy = 1;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            multipy *= Integer.valueOf(line);
                        }
                        return multipy;
                    }
                };
        return fileReadTemplate(filePath, multitlyCallback);
    }
    public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filePath));
            Integer res = initVal;
            String line = null;
            while((line = br.readLine()) != null){
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if(br != null){
                try{br.close();}
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws  IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filePath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if(br != null){
                try{br.close();}
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }


}
