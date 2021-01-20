package mongoDB;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.FindIterable.*;
import static com.mongodb.client.model.Updates.*;

public class MongoCRUD {

	public static void insertarDocumentos(MongoCollection<Document> alumnos)
	{
	/* Creamos un documento mediante new */
	
	alumnos.insertOne(Document.parse("{name:'Barry Potter',species:'human',gender:'male',house:'Gryffindor',dateOfBirth:'31-07-1980',"
			+ " yearOfBirth: 1980,ancestry:'half-blood',eyeColour:'green',hairColour:'black',"
					+ " wand:{wood:'holly',core:'phoenix feather',length:11},patronus:'stag',hogwartsStudent:true,"
							+ " hogwartsStaff: false, actor:'Daniel Radcliffe',alive:true,image:'http://hp-api.herokuapp.com/images/harry.jpg'}"));
	
	/* Insertamos varios documentos creados parseando strings */
	alumnos.insertMany(Arrays.asList(
			Document.parse("{name:'Javi El brujo',species:'human',gender:'male',house:'Gryffindor',dateOfBirth:'31-07-1980',"
					+ " yearOfBirth: 1980,ancestry:'half-blood',eyeColour:'green',hairColour:'black',"
							+ " wand:{wood:'holly',core:'phoenix feather',length:11},patronus:'stag',hogwartsStudent:true,"
									+ " hogwartsStaff: false, actor:'Daniel Radcliffe',alive:true,image:'http://hp-api.herokuapp.com/images/harry.jpg'}"),
			Document.parse("{name:'Martin Escobas',species:'human',gender:'male',house:'Gondor',dateOfBirth:'31-07-1960',"
					+ " yearOfBirth: 1980,ancestry:'half-blood',eyeColour:'green',hairColour:'black',"
							+ " wand:{wood:'holly',core:'phoenix feather',length:11},patronus:'stag',hogwartsStudent:true,"
									+ " hogwartsStaff: false, actor:'Daniel Radcliffe',alive:true,image:'http://hp-api.herokuapp.com/images/harry.jpg'}"),
			Document.parse("{name:'Lorena Cabras',species:'human',gender:'female',house:'Gryffindor',dateOfBirth:'31-07-1980',"
					+ " yearOfBirth: 1980,ancestry:'half-blood',eyeColour:'green',hairColour:'black',"
							+ " wand:{wood:'holly',core:'phoenix feather',length:11},patronus:'stag',hogwartsStudent:true,"
									+ " hogwartsStaff: false, actor:'Daniel Radcliffe',alive:true,image:'http://hp-api.herokuapp.com/images/harry.jpg'}")));
		
	}

	public static void consultarDocumentos(MongoCollection<Document> alumnos) {
		
		
		/* Mostrar todos los personajes cuyo atributo "species" tenga como valor "human". */
		FindIterable<Document>  buscaEspecie = alumnos.find(eq("species", "human"));

		/* Mostrar todos los personajes cuyo atributo "yearOfBirth" sea anterior a 1979 */
		FindIterable<Document> buscaAnio = alumnos.find(lte("yearOfBirth", 1979));
		
		/* Mostrar todos los personajes cuyo atributo "wood" de la propiedad "wand" sea "holly".*/
		FindIterable<Document>  buscaWood = alumnos.find(eq("wood.wand", "holly"));

		/* Mostrar todos los personajes que estén vivos (propiedad "alive" igual a true) y además sean
		 *  estudiantes (propiedad "hogwartsStudent" igual a true).*/
		FindIterable<Document>  buscaAlive = alumnos.find(and(eq("alive", true), eq("hogwartsStudent", true)));

	
		System.out.println("Mostramos todos los personajes cuyo atributo \"species\" tenga como valor \"human\": ");
		/* Recorremos todos los resultados y los mostramos */
		for (Object alumno : buscaEspecie)
		{
			System.out.println(((Document) alumno).toJson());	
	    }
		
		System.out.println("Mostramos todos los personajes cuyo atributo \"yearOfBirth\" sea anterior a 1979: ");
		/* Recorremos todos los resultados y los mostramos */
		for (Object alumno : buscaAnio)
		{
			System.out.println(((Document) alumno).toJson());	
	    }
		
		
		System.out.println("Mostramos todos los personajes cuyo atributo \"wood\" de la propiedad \"wand\" sea \"holly\": ");
		/* Recorremos todos los resultados y los mostramos */
		for (Object alumno : buscaWood)
		{
			System.out.println(((Document) alumno).toJson());	
	    }
		
		System.out.println("Mostramos todos los personajes que estén vivos (propiedad \"alive\" igual a true) y"
				+ " además sean estudiantes (propiedad \"hogwartsStudent\" igual a true): ");
		/* Recorremos todos los resultados y los mostramos */
		for (Object alumno : buscaAlive)
		{
			System.out.println(((Document) alumno).toJson());	
	    }
}

	public static void actualzarDocumentos(MongoCollection<Document> alumnos) {
		// TODO Auto-generated method stub
		/*Cambiamos el color de ojos y de pelo del personaje Harry Potter*/
		alumnos.updateOne(eq("name", "Harry Potter"), combine(set("eyeColour", "black"), set("hairColour", "green")));
		/*Cambiamos la fecha de nacimiento del personaje Hermione Granger*/
		alumnos.updateOne(eq("name", "Hermione Granger"), combine(set("yearOfBirth", 2000)));
		/*Cambiamos el nombre del personaje Barry Potter por el de Javier Lorca*/
		alumnos.updateMany(eq("name", "Barry Potter"), combine(set("name", "Javier Lorca")));
	
	}

	public static void eliminarDocumentos(MongoCollection<Document> alumnos) {
		// TODO Auto-generated method stub
		alumnos.deleteOne(eq("name","Martin Escobas"));
		alumnos.deleteMany(eq("name","Javier Lorca"));
		System.out.println("Eliminación realizada correctamente");
	}
}
