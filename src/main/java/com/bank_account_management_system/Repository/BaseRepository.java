package com.bank_account_management_system.Repository;



import com.bank_account_management_system.model.BankAccount;

import java.io.*;
import java.util.ArrayList;

  abstract class BaseRepository<T> {

     protected final String separator = "#//#";
     private final String fileName;

     public BaseRepository(String fileName) {
          this.fileName = fileName;
      }

     //=========common methods=========
     protected boolean appendLine(String Line)  {

        boolean flag=false;
       try( BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true));) {
           bw.write(Line+"\n");
           flag= true;
       }
        catch(IOException e) {
            return false;

        }


        return flag;
    }

     protected boolean resetFile() {
          try (BufferedWriter bw =
                       new BufferedWriter(new FileWriter(fileName,false))) {

          }
          catch (IOException e) {
              return false;
          }
          return true;
      }

     //for help
     protected String commonFormat(BankAccount object) {
         return  object.getAccountId()+separator+
                 object.getPassword()+separator+
                 object.getDateCreated()+separator+
                 object.getHolderName()+separator+
                 object.getBalance();
     }


    //========CRUD Methods=======
     public boolean add(T object){
          return appendLine(format(object));
      }

     public ArrayList<T> getAll(){

          ArrayList<T> Accounts = new ArrayList<>();

          try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {

              String line;
              T account;

              while ((line = br.readLine()) != null)
              {
                  account =  parse(line);

                  if(account!=null) {
                      Accounts.add(account);
                  }
              }

          }
          catch(IOException e) {
              return Accounts;

          }

          return Accounts;
      }

     public T findById(int accountId) {

          for( T account : getAll() ) {

              if (accountId ==getId(account))
                  return account;

          }

          return null;
      }

     public boolean delete(int accountId) {
          boolean result = false;

          ArrayList<T> accounts = getAll();

          //to reset file to can fill file again
          if(resetFile()) {
              for (T account :  accounts) {
                  //skip
                  if (getId(account) == accountId) {
                      result = true;
                      continue;
                  }

                  //fill file
                  appendLine(format(account));

              }
          }
          return result;
      }

     public boolean update(T object) {

          boolean result = false;

          ArrayList<T> accounts = getAll();

          //to clear file
          if(resetFile())
          {
              for(T account : accounts) {
                  //update data
                  if(getId(account) == getId(object)) {
                      account =  object;
                      result = true;

                  }
                  //fill file
                  appendLine(format(account));

              }
          }

          return result;


      }



      //===========abstract methods============
     protected abstract String format(T object);
     protected abstract T  parse(String line);
     protected abstract int getId(T object);


  }
