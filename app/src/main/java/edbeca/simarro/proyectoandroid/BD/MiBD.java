package edbeca.simarro.proyectoandroid.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;

import edbeca.simarro.proyectoandroid.dao.PeliculaDAO;
import edbeca.simarro.proyectoandroid.dao.UsuarioDAO;

public class MiBD extends SQLiteOpenHelper implements Serializable {

    private static SQLiteDatabase db;

    private static final String database = "MisPeliculas";

    //versión de la base de datos
    private static final int version = 19;

    //Instrucción SQL para crear la tabla de Usuarios
    private String sqlCreacionUsuarios = "CREATE TABLE usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, " +
            "claveSeguridad STRING, email STRING);";

    //Creacion de la tabla peliculas
    private String sqlCreacionPeliculas = "CREATE TABLE peliculas ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "titulo STRING, duracion INTEGER, protagonista STRING, valoracion INTEGER, idUsuario INTEGER);";

    private static MiBD instance = null;

    private static UsuarioDAO usuarioDAO;
    private static PeliculaDAO peliculaDAO;

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public PeliculaDAO getPeliculaDAO(){
        return peliculaDAO;
    }

    public static MiBD getInstance(Context context) {
        if(instance == null) {
            instance = new MiBD(context);
            db = instance.getWritableDatabase();
            usuarioDAO = new UsuarioDAO();
            peliculaDAO = new PeliculaDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB(){
        return db;
    }
    public static void closeDB(){db.close();};

    /**
     * Constructor de clase
     * */
    public MiBD(Context context) {
        super( context, database, null, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionUsuarios);
        db.execSQL(sqlCreacionPeliculas);
        insercionDatos(db);
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion  );
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS usuarios" );
            db.execSQL( "DROP TABLE IF EXISTS peliculas" );
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionUsuarios);
            db.execSQL(sqlCreacionPeliculas);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
        }
    }

    private void insercionDatos(SQLiteDatabase db){
        // Insertamos los usuarios
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (1, 'pedro', '1234', 'pedro.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (2, 'oscar', '1234', 'oscar.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (3, 'pepe', '1234', 'pepe.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (4, 'mortadelo', '1234', 'mortadelo.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (5, 'sancho', '1234', 'sancho.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (6, 'quijote', '1234', 'quijote.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (7, 'jorge', '1234', 'jorge.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (8, 'jordi', '1234', 'jordi.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (9, 'borja', '1234', 'borja.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (10, 'miguel', '1234', 'miguel.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (11, 'andreu', '1234', 'andreu.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (12, 'laura', '1234', 'laura.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (13, 'dani', '1234', 'dani.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (14, 'manu', '1234', 'manu.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (15, 'herme', '1234', 'herme.pitia.es');");


        //INSERTAMOS LAS PELICULAS
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (1, 'The Machinist', 90, 'Christian Bale', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (2, 'Requiem for a dream', 94, 'Jared Leto', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (3, 'Joker', 88, 'Joaquin Phoenix', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (4, 'La comunidad del anillo', 90, 'Viggo Mortensen', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (5, 'Las dos torres', 90, 'Viggo Mortensen', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (6, 'El retorno del rey', 90, 'Viggo Mortensen', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (7, 'Insidius', 94, 'Patrick Wilson', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (8, 'Split', 90, 'James Mcavoy', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (9, 'Prometheus', 90, 'Michael Fassbender', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (10, 'American Phsyco', 90, 'Christian Bale', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (11, 'El caballero negro', 90, 'Christian Bale', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (12, 'Perfect blue', 90, 'Junko Iwao', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (13, 'El caso Enfield', 90, 'Patrick Wilson', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (14, 'Cisne Negro', 90, 'Natalie Portman', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (15, 'The Machinist', 90, 'Christian Bale', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (16, 'Noe', 90, 'Russell Crow', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (17, 'El luchador', 90, 'Mickey Rorke', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (18, 'Pi', 90, 'Samia aoab', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (19, 'Mother!', 90, 'Javier Bardem', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (20, 'La fuente de la vida', 90, 'Hugh Jackman', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (21, 'Peter Pan', 90, 'Hugh Jackman', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (22, 'The Fighter', 90, 'Christian Bale', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (23, 'Zipper', 90, 'Patrick Wilson', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (24, 'Alien', 90, 'Michael Fassbender', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (25, 'La tumba de las luciernagas', 90, 'Ahru', 5, 12);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (26, 'Al Pachino', 90, 'Marlon Brando', 5, 7);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (27, 'El mago de Oz', 94, 'Judi Garland', 5, 9);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (28, 'Ciudadano Kane', 88, 'Orson Welles', 5, 8);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (29, 'Cadena Perpetua', 90, 'Tim Robbins', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (30, 'Pulp Fiction', 90, 'John Travolta', 5, 11);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (31, 'El retorno del rey', 90, 'Viggo Mortensen', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (33, 'CasaBlanca', 94, 'Michel Curtiz', 5, 2);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (34, 'Underworld', 90, 'Kate Beckinsale', 5, 3);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (35, 'Martyrs', 90, 'Alaoui', 5, 4);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (36, 'Evil dead', 90, 'Bruce Campbell', 5, 5);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (37, 'La piel que habito', 90, 'Penelope cruz', 5, 6);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (38, 'El laberiento del fauno', 90, 'Ivana Baquero', 5, 13);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (39, 'REC', 90, 'Manuela Velasco', 5, 14);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (40, 'Gladiator', 90, 'Russel Crowe', 5, 7);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (41, 'El velo pintado', 90, 'Naomi Watts', 5, 7);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (42, 'Captain Fantastic', 90, 'Viggo Mortensen', 5, 7);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (43, 'Ocho apellidos vascos', 90, 'Dani Rovira', 5, 7);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (44, '8 milles', 90, 'Eminem', 5, 8);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (45, 'Good time!', 90, 'Robert Patinson', 5, 9);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (46, 'The lightHouse', 90, 'William Dafoe', 5, 9);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (47, 'Tenet', 90, 'Robert Pattinson', 5, 12);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (48, 'Luna Nueva', 90, 'Robert Pattinson', 5, 13);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (49, 'El humano que hizo llorar a Satán', 90, 'Fudo Akira', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (50, 'Seven', 90, 'Brad pitt', 5, 14);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (51, 'Interstellar', 90, 'Mathew McConauGhey', 5, 10);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (52, 'Jurassic World', 90, 'Chris Pratt', 5, 11);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (53, 'Mad Max', 94, 'Tom Hardy', 5, 12);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (54, 'Renacido', 88, 'Leonardo DiCacrio', 5, 13);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (55, 'El resplandor', 90, 'Jack Nicolson', 5, 14);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (56, 'Recuerdame', 90, 'Robert Pattinson', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (57, 'El diablo a todas horas', 90, 'Robert Pattinson', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (58, 'Agua para elefentes', 94, 'Robert Pattinson', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (59, 'Bel-Ami', 90, 'Robert Pattinson', 5, 2);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (60, 'Capone', 90, 'Tom Hardy', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (61, 'Dunkerke', 90, 'Tom Hardy', 5, 3);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (62, 'El silencio de los corderos', 90, 'Joddie Foster', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (63, 'El llanero solitario', 90, 'Johnny Depp', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (64, 'Rango', 90, 'Johnny Depp', 5, 2);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (65, 'Mordecai', 90, 'Johnny Depp', 5, 5);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (66, 'BraveHeart', 90, 'Mel Gibson', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (67, 'Paprika', 90, 'Satoshi Kon', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (68, 'Blame!', 90, 'Sakurai', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (69, 'Glass', 90, 'James McAvoy', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (70, 'Wanted', 90, 'James McAvoy', 5, 6);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (71, 'Atonement', 90, 'James McAvoy', 5, 7);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (72, 'Soy leyenda', 90, 'Will Smith', 5, 1);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (73, 'Aladdin', 90, 'Will Smith', 5, 8);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (74, 'Yo Robot', 90, 'Will Smith', 5, 15);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (75, 'Siete almas', 90, 'Will Smith', 5, 9);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (76, 'La tumba de las luciernagas', 90, 'Ahru', 5, 12);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (77, 'Enemigo Público', 90, 'Will Smith', 5, 10);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (78, 'Bad boys', 94, 'Will Smith', 5, 11);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (79, 'Focus', 88, 'Will Smith', 5, 8);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (80, 'Men in Black', 90, 'Will Smith', 5, 14);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (81, 'Suicide Squad', 90, 'Will Smith', 5, 11);");
        db.execSQL("INSERT INTO peliculas(id, titulo, duracion, protagonista, valoracion, idUsuario) VALUES (82, 'Hitch', 90, 'Will Smith', 5, 15);");



    }

}
