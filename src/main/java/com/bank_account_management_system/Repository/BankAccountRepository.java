package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.CheckingAccount;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

  class BankAccountRepository {

     static String separator = "#//#";

     //common methods
     static boolean uploadLineToFile(String Line,String fileName)  {

        boolean flag=false;
       try( BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true));) {
           bw.write(Line+"\n");
           flag= true;
       }
        catch(IOException e) {
            throw new RuntimeException(e);

        }


        return flag;
    }

     static String format(int accountId, String password,LocalDateTime LocalDateTime , String holderName,double balance){
        return  accountId+separator+password+separator+ LocalDateTime+separator+holderName+separator+balance;
    }

     static ArrayList<String> loadDataWithoutSplit(String fileName){

        ArrayList<String> fileData = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null)
                fileData.add(line);
        }
        catch(IOException e) {
            throw new RuntimeException(e);

        }

        return fileData;
    }

    static void clearFile(String fileName) {
        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(fileName,false))) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     static boolean isExist(int accountId,String fileName){

       String line;
       ArrayList<String> arrayFileData =loadDataWithoutSplit(fileName);

       for (String accountData : arrayFileData) {
           if(Integer.parseInt(accountData.split(separator)[0]) == accountId){
               return true;
           }
       }

        return false;
    }






}
