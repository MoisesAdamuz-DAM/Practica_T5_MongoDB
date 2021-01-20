package mongoDB;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class Test {

	public static void main(String[] args) {
		//Establecemos la conexión con la base de datos
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		//Creamos la base de datos
		MongoDatabase database = conexion.getDatabase("ADP5");
		//Creamos la coleccion de documentos
		MongoCollection<Document> alumnos = database.getCollection("harry-potter");
		
//		MongoCRUD.insertarDocumentos(alumnos);
//		MongoCRUD.consultarDocumentos(alumnos);
//		MongoCRUD.actualzarDocumentos(alumnos);
		MongoCRUD.eliminarDocumentos(alumnos);

	}

}
