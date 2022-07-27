/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
public class DateHeplper {
 static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
  
  public static Date toDate(String date, String... pattern){
      try {
           if (pattern.length > 0) {
                    formater.applyPattern(pattern[0]);
                } 
                if(date == null){
                    return DateHeplper.now();
                }else{
                    return formater.parse(date);
                }
      } catch (ParseException ex) {
          throw  new RuntimeException(ex);
      }
  }
  
  public static String toString(Date date, String... pattern){
        if (pattern.length > 0) {
            formater.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = DateHeplper.now();
        }
      return formater.format(date);
  }
  
  public static  Date now(){
      return new Date();
  }
  
  public static Date aadDays(Date date, long days){
      date.setTime(date.getTime() +days *24*60*60*1000);
      return date;
  }
}
