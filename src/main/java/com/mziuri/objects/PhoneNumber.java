package com.mziuri.objects;

import jakarta.persistence.*;

@Entity
@Table(name = "phone_number")
@NamedQuery(name = "find number", query = "from PhoneNumber p")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    public void save() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mziuri");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.persist(this);
            manager.getTransaction().commit();
        } finally {
            manager.close();
            factory.close();
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return name + " - " + number;
    }
}
