package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class OperacionesCRUD {

    static String urlConexionP = "jdbc:postgresql://examenad.ci66saah1axn.us-east-1.rds.amazonaws.com:5432/morseg";
    static String usuarioP = "postgres";
    static String passwordP = "qwerty1234";

    static String uri ="mongodb://morseg:qwerty1234@ec2-54-146-188-92.compute-1.amazonaws.com:27017/morseg";

    public static void CrearCliente(Clients c){

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase("morseg").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Clients> collection = database.getCollection("clients", Clients.class);

            collection.insertOne(c);
        }
        catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void CrearCuenta(Accounts a){

        try (Connection conexion = DriverManager.getConnection(urlConexionP, usuarioP, passwordP)){

            String insercionSQL = "INSERT INTO accounts (iban, balance, clientid) VALUES (?, ?, ?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1,a.getIban());
            insercion.setFloat(2,a.getBalance());
            insercion.setInt(3,a.getClientid());

            insercion.executeUpdate();
            insercion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void BorrarCuenta(Accounts a){
        try (Connection conexion = DriverManager.getConnection(urlConexionP, usuarioP, passwordP)){

            String insercionSQL = "DELETE FROM accounts WHERE accountid = ?";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1,a.getAccountid());
            insercion.executeUpdate();
            insercion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void BorrarCliente(Clients c){

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase("morseg").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Clients> collection = database.getCollection("clients", Clients.class);

            collection.deleteOne(eq("clientid", c.getClientid()));
            BorrarCuentasCliente(c.getClientid());
        }
        catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

    }
    public static void BorrarCuentasCliente(String idCliente){


        try (Connection conexion = DriverManager.getConnection(urlConexionP, usuarioP, passwordP)){

            String insercionSQL = "DELETE FROM accounts WHERE clientid = ?";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setInt(1, Integer.parseInt(idCliente));

            insercion.executeUpdate();
            insercion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarCuenta(Accounts a){
        try (Connection conexion = DriverManager.getConnection(urlConexionP, usuarioP, passwordP)){

            String updateSQL = "UPDATE accounts SET iban=?, balance=?, clientid=? WHERE accountid=?";
            PreparedStatement update = conexion.prepareStatement(updateSQL);
            update.setString(1,a.getIban());
            update.setFloat(2,a.getBalance());
            update.setInt(3,a.getClientid());
            update.setString(4,a.getAccountid());

            int filasActualizadas = update.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Cuenta actualizado correctamente.");
            } else {
                System.out.println("No se encontró ninguna cuenta con el ID proporcionado.");
            }

            update.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarCliente(Clients c){

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase("morseg").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Clients> coleccionPilotos = database.getCollection("clients", Clients.class);

            coleccionPilotos.replaceOne(eq("clientid", c.getClientid()), c);

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static List<Accounts> LeerCuentas() {
        Accounts a;
        List<Accounts> cuentas = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(urlConexionP, usuarioP, passwordP)) {
            String consultaSQL = "SELECT *" +
                    "FROM accounts ";
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                a = new Accounts(resultados.getString("accountid"), resultados.getString("iban"), resultados.getFloat("balance"),resultados.getInt("clientid"));
                cuentas.add(a);
            }

            cuentas.stream().forEach(System.out::println);
            consulta.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cuentas;
    }
    public static List<Clients> leerClientes(){
        try(MongoClient mongoClient = MongoClients.create(uri)){
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase("morseg").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Clients> clientes = database.getCollection("clients", Clients.class);

            List<Clients> pilotos = clientes.find().into(new ArrayList<>());
            return pilotos;

        }
        catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }
}



