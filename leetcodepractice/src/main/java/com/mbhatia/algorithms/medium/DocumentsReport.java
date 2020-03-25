package com.mbhatia.algorithms.medium;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

//https://coderpad.io/JNAETJM7
class Document {
    String name;
    String description;
    String createdBy;
    String lastModifiedBy;
    Long sizeInBytes;
    Long createdTime;
    Long modifiedTime;

    public Document() {
    }

    public Document(String name, String description, String createdBy, String lastModifiedBy, Long sizeInBytes, Long createdTime, Long modifiedTime) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.sizeInBytes = sizeInBytes;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    /**
     * Prints a report of the list of documents in the following format:
     *
     * Group by document.createdBy
     * Sort the groups using document.createdBy ascending, case insensitive
     *      Sort each sub list of documents by document.createdTime ascending
     * Format the output of document.size to be a more friendly format. Ex.  50 mb, 900 k, 342 bytes, etc...
     * Format the dates using the format: yyyy-MM-dd
     * Format the output of document.description such that
     *  - no more than the first 25 characters of the description are displayed
     *  - don't truncate any words unless the first word is longer than 25 characters
     *  - display "..." at the end of the description to indicate that it has been truncated
     *  (these three characters do not count as part of the 25 character limit)
     *
     * Example:
     * Andy Andrews
     *      "Bobby Timmons Biography","An exhaustive look at the ...",233 mb,2013-05-09,2013-05-14
     *      "Apple Sauce","Study of apple sauces.”,87 gb,2013-05-10,2013-05-10
     *      "Zed","All matters, A to Zed”,924 k,2013-05-12,2013-05-12
     * Janet Smith
     *      "Xray","How the Xray shows your ...",48 mb,2010-10-22,2010-12-02
     *      "Computers","Inventory list of ...",423 bytes,2013-03-01,2013-02-17
     *
     *
     * @param documents not null
     */
    public void printDocumentsReport(List<Document> documents) {
        //Group documents by createdBy
        Map<String, List<Document>> docsForPrinting = documents.stream().collect(Collectors.groupingBy(document -> document.createdBy));
        //Sort the map by key values ascending, case insensitive
        docsForPrinting = docsForPrinting.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareToIgnoreCase(e2.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        //Sort each sub-list by createdTime ascending
        for(Map.Entry<String, List<Document>> entry : docsForPrinting.entrySet()){
            entry.getValue().sort(Comparator.comparing(document -> document.createdTime));
        }

        //Print document report
        for(Map.Entry<String, List<Document>> entry : docsForPrinting.entrySet()){
            System.out.println(entry.getKey());
            for(Document doc:entry.getValue()){
                System.out.println("\t\"" + doc.name + "\",\""
                        + getFormattedDescription(doc.description) + "\","
                        + getHumanReadableFileSize(doc.sizeInBytes) + ","
                        + getDateFromMilliseconds(doc.createdTime) + ","
                        + getDateFromMilliseconds(doc.modifiedTime)
                );
            }
        }
    }

    /*
     * This method assumes that the input time is provided in milliseconds (long)
     * and converts it to the yyyy-MM-dd format date
     */
    private String getDateFromMilliseconds(Long millis){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(millis);
        return df.format(date);
    }

    /*
    * This methods takes file size in bytes as input
    * and returns the size in more human readable form (e.g. in KB/MB/GB)
    */
    private String getHumanReadableFileSize(Long fileSizeInBytes){
        long KB = 1024L, MB = KB*KB, GB = MB*KB, TB = GB*KB;
        long[] units = new long[]{TB, GB, MB, KB, 1};
        String[] sizeSymbol = new String[]{"tb", "gb", "mb", "k", "bytes"};
        int length = units.length;
        String formattedFileSize = null;

        if(fileSizeInBytes < 1)
            throw new IllegalArgumentException("File size should be a positive non-zero integer");
        for(int i=0; i<length; ++i){
            if(fileSizeInBytes > units[i]){
                formattedFileSize = new DecimalFormat("#.##").format(fileSizeInBytes/units[i]) + " " + sizeSymbol[i];
                break;
            }
        }
        return formattedFileSize;
    }

    /*
    * This method shortens the description into complete words
    * with a max cumulative length of 25 characters
    * */
    private String getFormattedDescription(String fullDescription){
        StringBuilder builder = new StringBuilder();
        String[] words = fullDescription.split(" ");
        int wordCount = words.length, i;
        boolean contentTruncated = false;

        for (i=0; i<wordCount; ++i){
            String word = words[i];
            int builderLength = builder.length(), wordLength = word.length();

            if(builderLength < 25){
                if(builderLength + wordLength <= 25){ //Complete word can be accommodated in the description
                    builder.append(word).append(" ");
                }
                else{
                    if(builderLength == 0){ //First word itself is longer than 25 characters; Truncate it and append
                        builder.append(word, 0, 25).append(" ");
                        contentTruncated = true;
                    }
                    else{
                        //Skip and end
                        contentTruncated = true;
                        break;
                    }
                }
            }
            else {
                break;
            }
        }
        if(i<wordCount || contentTruncated) //There is more content in the description
            builder.append("...");
        return builder.toString().trim();
    }
}

public class DocumentsReport {
    public static void main(String[] args) {
        Document d1 = new Document("Bobby Timmons Biography", "An exhaustive look at the asjdhgjasg jhsgfdjytuhgj",
                "Andy Andrews", "Munish Bhatia", 244318208L, 1368144000000L, 1368489600000L);

        Document d2 = new Document("Apple Sauce", "Study of apple sauces.",
                "abdy Andrews", "Ted Bonet", 93415538688L, 1368057600000L, 1368144000000L);

        Document d3 = new Document("Zed", "All matters, A to Zed",
                "Andy Andrews", "Andy Marchewka", 946176L, 1368316800000L, 1368316800000L);

        Document d4 = new Document("Xray", "How the Xray shows your bones and skeleton",
                "Janet Smith", "Jason Emery", 50331648L, 1287705600000L, 1291248000000L);

        Document d5 = new Document("Computers", "Inventorylistofcomputersbuiltbymankind.",
                "Janet Smith", "Alex K", 423L, 1362096000000L, 1361059200000L);

        List<Document> documents = new ArrayList<>(5);
        documents.add(d4);
        documents.add(d5);
        documents.add(d1);
        documents.add(d2);
        documents.add(d3);

        Document obj = new Document();
        obj.printDocumentsReport(documents);
    }
}
