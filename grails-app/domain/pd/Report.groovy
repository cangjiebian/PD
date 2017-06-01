package pd

import javax.persistence.Transient
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat

import static pd.Application.reportDir


class Report implements Comparable<Report>{
    
    String name
    Paper paper
    static def timeFormat=new SimpleDateFormat('MM-dd-a-h-mm',Locale.CHINA)
    Date createdAt
    
    @Transient
    File reportTmpFile
    
    static belongsTo = [paper:Paper]
    static mapping = {
        paper lazy: false
    }
    
/*    static constraints = {}
    
    */
    
    Report(){}
    Report(Paper paper,File reportTmpFile){
        this.paper=paper
        this.reportTmpFile=reportTmpFile
        createdAt=new Date()
        this.name="$paper.name-${timeFormat.format(createdAt)}-检测报告"
    }
    
    
    def afterInsert(){
        Files.move(reportTmpFile.toPath(),(this as File).with{parentFile.mkdirs();toPath()})
    }
    
    def asType(Class type){
        if(type==File){
            return new File(reportDir,"$id/${name}.txt")
        }
    }
    
    int compareTo(Report another){
        createdAt<=>another.createdAt
    }
}
