package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<5000)throw new Exception("Insufficient Balance");
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }


    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
      if(checkValid(tradeLicenseId)) {
         tradeLicenseId="";
      }
      else if(reArrange(tradeLicenseId)){
      }
      else throw new Exception("Valid License can not be generated");
    }

    boolean checkValid(String id){
      for(int i=0;i<id.length()-1;i++)
          if(id.charAt(i)==id.charAt(i+1))return false;
      return true;
    }
    boolean reArrange(String id){
        int []c=new int[26];
        for(int i=0;i<id.length();i++){
            int ch=id.charAt(i)-'A';
            c[ch]++;
        }
        int max_freq=0;
        char ele='A';
        for(int i=0;i<26;i++) {
            max_freq = Math.max(max_freq, c[i]);
            ele=(char)(i+'A');
        }
        if(id.length()%2==0){
            if(max_freq>id.length()/2)
                return false;
        }
        if(id.length()%2!=0){
            if(max_freq>(id.length()/2)+1)
                return false;
        }
        char []ch=new char[id.length()];
        String ans="";
        int index=0;
        for(index=0;index<id.length();index+=2){
            if(max_freq>0) {
                ch[index] = ele;
                max_freq--;
            }
            else break;
        }
        for(int i=0;i<26;i++){
            char c1=(char)(i+'A');
            while (c[i]>0){
                if(index>id.length()-1)
                    index=1;
                ch[index]=c1;
            }
        }
        for(int i=0;i<ch.length;i++)
            ans+=ch[i];
        tradeLicenseId=ans;
        return true;
    }
}
