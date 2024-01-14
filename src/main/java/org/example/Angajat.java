package org.example;

public class Angajat {

    private String nume;
    private String prenume;

    private String departament;

    private boolean tip_job;  //full-time=1, part-time=0

    private boolean statut_angajat; //casatorit=1 , necasatorit=0

    private String descriere_job;



    public Angajat(String nume, String prenume, String departament, boolean  tip_job, boolean statut_angajat, String descriere_job)
    {
        this.nume=nume;
        this.prenume=prenume;
        this.departament=departament;
        this.tip_job=tip_job;
        this.statut_angajat=statut_angajat;
        this.descriere_job=descriere_job;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public boolean getTip_job() {
        return tip_job;
    }

    public void setTip_job(boolean  tip_job) {
        this.tip_job = tip_job;
    }

    public boolean getStatut_angajat() {
        return statut_angajat;
    }

    public void setStatut_angajat(boolean statut_angajat) {
        this.statut_angajat = statut_angajat;
    }

    public String getDescriere_job() {
        return descriere_job;
    }

    public void setDescriere_job(String descriere_job) {
        this.descriere_job = descriere_job;
    }
}
