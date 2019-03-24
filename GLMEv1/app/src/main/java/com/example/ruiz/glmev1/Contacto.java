package com.example.ruiz.glmev1;

public class Contacto {
    private String id;
    private String nombre;
    private String telefono;
    private String email;

    public Contacto(String id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        Contacto obj=(Contacto)o;
        if(obj.getNombre().equals(this.nombre)){
            return true;
        }else {
            return false;
        }
    }
    

    public Contacto(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
