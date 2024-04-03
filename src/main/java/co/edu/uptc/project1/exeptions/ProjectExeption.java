package co.edu.uptc.project1.exeptions;

public class ProjectExeption extends Exception {
private TypeMessage typeMessage;

    public ProjectExeption(TypeMessage typeMessage) {
        super(typeMessage.message);
        this.typeMessage = typeMessage;        
    }
    
    public Message getExceptionMessage(){
        return new Message(this.typeMessage.code,
         this.typeMessage.message, 
         this.typeMessage.codeHttp);
            
    }
    
}
