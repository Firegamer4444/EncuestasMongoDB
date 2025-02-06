package aed;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String URI = "mongodb://localhost:27017"; // Reemplaza con tu URI si usas Mongo Atlas
    private static final String DATABASE_NAME = "encuestaDB";

    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
        }
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
