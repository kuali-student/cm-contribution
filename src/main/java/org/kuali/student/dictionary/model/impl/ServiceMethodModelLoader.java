/*
 * Copyright 2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may	obtain a copy of the License at
 *
 * 	http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.dictionary.model.impl;

import org.kuali.student.dictionary.model.validation.DictionaryValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.kuali.student.dictionary.model.ServiceMethod;
import org.kuali.student.dictionary.model.ServiceMethodError;
import org.kuali.student.dictionary.model.ServiceMethodModel;
import org.kuali.student.dictionary.model.ServiceMethodParameter;
import org.kuali.student.dictionary.model.ServiceMethodReturnValue;
import org.kuali.student.dictionary.model.ServiceMethodRow;
import org.kuali.student.dictionary.model.spreadsheet.SpreadsheetReader;
import org.kuali.student.dictionary.model.spreadsheet.WorksheetReader;

/**
 * Loads a spreadsheet using either a google or excel reader
 * @author nwright
 */
public class ServiceMethodModelLoader implements ServiceMethodModel
{

 private SpreadsheetReader spreadsheetReader;

 public ServiceMethodModelLoader (SpreadsheetReader spreadsheetReader)
 {
  this.spreadsheetReader = spreadsheetReader;
 }

 public List<ServiceMethod> getServiceMethods ()
 {
  WorksheetReader worksheetReader =
   spreadsheetReader.getWorksheetReader ("Methods");
  List<ServiceMethod> list = new ArrayList ();
  ServiceMethod serviceMethod = null;
  int rowNumber = 1;
  String lastKey = "";
  while (worksheetReader.next ())
  {
   rowNumber ++;
   ServiceMethodRow row = new ServiceMethodRow ();
   loadRow (worksheetReader, row, rowNumber);
   if (isBlankRow (row))
   {
    continue;
   }
   String key = row.getKey ();
   if (key.equals (""))
   {
    key = lastKey;
   }
   lastKey = key;
   if (key.equalsIgnoreCase ("Method"))
   {
    serviceMethod = new ServiceMethod ();
    serviceMethod.setService (row.getService ());
    serviceMethod.setName (row.getShortName ());
    list.add (serviceMethod);
   }
   else if (key.equalsIgnoreCase ("Description"))
   {
    serviceMethod.setDescription (row.getShortName ());
   }
   else if (key.equalsIgnoreCase ("Parameters"))
   {
    if ( ! row.getShortName ().equalsIgnoreCase ("None"))
    {
     ServiceMethodParameter param = new ServiceMethodParameter ();
     param.setName (row.getLongName ());
     param.setType (row.getShortName ());
     param.setDescription (row.getDescription ());
     serviceMethod.getParameters ().add (param);
    }
   }
   else if (key.equalsIgnoreCase ("Return"))
   {
    ServiceMethodReturnValue returnValue = new ServiceMethodReturnValue ();
    returnValue.setType (row.getShortName ());
    returnValue.setDescription (row.getLongName ());
    serviceMethod.setReturnValue (returnValue);
   }
   else if (key.equalsIgnoreCase ("Errors"))
   {
    ServiceMethodError error = new ServiceMethodError ();
    error.setType (row.getShortName ());
    error.setDescription (row.getLongName ());
    serviceMethod.getErrors ().add (error);
   }
   else if (key.equalsIgnoreCase ("Capabilities"))
   {
    // all atp methods have these as empty so do nothing for now
   }
   else if (key.equalsIgnoreCase ("Use Cases"))
   {
    // all atp methods have these as empty so do nothing for now
   }
   else if (key.equalsIgnoreCase ("Comments/Feedback"))
   {
    // all atp methods have these as empty so do nothing for now
   }
   else

   {
    throw new DictionaryValidationException ("Spreadsheet row #" + rowNumber +
     " has an unknown key,[" + key + "]");
   }
  }
  return list;
 }

 private boolean isBlankRow (ServiceMethodRow row)
 {
  if ( ! row.getService ().equals (""))
  {
   return false;
  }
  if ( ! row.getKey ().equals (""))
  {
   return false;
  }
  if ( ! row.getShortName ().equals (""))
  {
   return false;
  }
  if ( ! row.getLongName ().equals (""))
  {
   return false;
  }
  if ( ! row.getDescription ().equals (""))
  {
   return false;
  }
  return true;
 }

 private void loadRow (WorksheetReader worksheetReader, ServiceMethodRow row,
                       int rowNumber)
 {
  row.setRowNumber (rowNumber);
  row.setService (getFixup (worksheetReader, "Service"));
  row.setKey (getFixup (worksheetReader, "Key"));
  row.setShortName (getFixup (worksheetReader, "ShortName"));
  row.setLongName (getFixup (worksheetReader, "LongName"));
  row.setDescription (getFixup (worksheetReader, "Description"));
 }

 private String get (WorksheetReader worksheetReader, String colName)
 {
  return get (worksheetReader, colName, null);
 }

 private String get (WorksheetReader worksheetReader,
                     String colName,
                     String colName2)
 {
  while (true)
  {
   if (worksheetReader.getIndex (colName) == -1)
   {
    if (colName2 != null)
    {
     colName = colName2;
    }
   }
   String value = worksheetReader.getValue (colName);
   if (value == null)
   {
    return "";
   }
   value = value.trim ();
   return value;
  }
 }

 private String getFixup (WorksheetReader worksheetReader, String colName)
 {
  return fixup (get (worksheetReader, colName));
 }

 private String getFixup (WorksheetReader worksheetReader, String colName,
                          String colName2)
 {
  return fixup (get (worksheetReader, colName, colName2));
 }

 private String fixup (String str)
 {
  if (str.equals ("FALSE"))
  {
   return "false";
  }
  if (str.equals ("TRUE"))
  {
   return "true";
  }
  return str;
 }

 @Override
 public List<String> getSourceNames ()
 {
  return Arrays.asList (spreadsheetReader.getSourceName ());
 }

}
