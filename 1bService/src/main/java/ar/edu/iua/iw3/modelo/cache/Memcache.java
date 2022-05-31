package ar.edu.iua.iw3.modelo.cache;

import ar.edu.iua.iw3.modelo.Historico.Historico;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.InetSocketAddress;


@SuppressWarnings("serial")
public class Memcache{

private   MemcachedClient mcc;

    public Memcache() throws IOException {
        this.mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11311));
        // Connecting to Memcached server on localhost
}

    public String buscar(String id){
        String dato = "";
        dato = (String) mcc.get(id);
        return mcc.get(id) == null ? null : dato;
    }



    public boolean agregar(Historico historico,String key, int tiempo){
        return  mcc.add(key, tiempo, historico.getJson(historico)).isDone();
    }


    public boolean actualizar(Historico historico,int tiempo){
        return  mcc.set(String.valueOf(historico.getId_historico()), tiempo, historico.getJson(historico)).isDone();
    }


    public boolean eliminar(Long id){
        return mcc.delete(String.valueOf(id)).isDone();
    }
}