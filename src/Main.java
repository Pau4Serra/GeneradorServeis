import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static ArrayList<Nen> nens = getNens();
    static ArrayList<Monitor> munis = getMonitors();

    public static void main(String[] args) {

        System.out.println();
        System.out.println("███████╗███████╗██████╗ ██╗   ██╗███████╗██╗███████╗\n" +
                "██╔════╝██╔════╝██╔══██╗██║   ██║██╔════╝██║██╔════╝\n" +
                "███████╗█████╗  ██████╔╝██║   ██║█████╗  ██║███████╗\n" +
                "╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██╔══╝  ██║╚════██║\n" +
                "███████║███████╗██║  ██║ ╚████╔╝ ███████╗██║███████║\n" +
                "╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝╚═╝╚══════╝\n" +
                "                                                    \n" +
                "███╗   ██╗███████╗███╗   ██╗███████╗                \n" +
                "████╗  ██║██╔════╝████╗  ██║██╔════╝                \n" +
                "██╔██╗ ██║█████╗  ██╔██╗ ██║███████╗                \n" +
                "██║╚██╗██║██╔══╝  ██║╚██╗██║╚════██║                \n" +
                "██║ ╚████║███████╗██║ ╚████║███████║                \n" +
                "╚═╝  ╚═══╝╚══════╝╚═╝  ╚═══╝╚══════╝                ");

        generarServeisNens();

        System.out.println();
        System.out.println("███████╗███████╗██████╗ ██╗   ██╗███████╗██╗███████╗\n" +
                "██╔════╝██╔════╝██╔══██╗██║   ██║██╔════╝██║██╔════╝\n" +
                "███████╗█████╗  ██████╔╝██║   ██║█████╗  ██║███████╗\n" +
                "╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██╔══╝  ██║╚════██║\n" +
                "███████║███████╗██║  ██║ ╚████╔╝ ███████╗██║███████║\n" +
                "╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝╚═╝╚══════╝\n" +
                "                                                    \n" +
                "███╗   ███╗██╗   ██╗███╗   ██╗██╗███████╗           \n" +
                "████╗ ████║██║   ██║████╗  ██║██║██╔════╝           \n" +
                "██╔████╔██║██║   ██║██╔██╗ ██║██║███████╗           \n" +
                "██║╚██╔╝██║██║   ██║██║╚██╗██║██║╚════██║           \n" +
                "██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║███████║           \n" +
                "╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝╚══════╝           ");

        generarServeisMunis();
    }

    public static ArrayList<Nen> getNens() {
        Gson gson = new Gson();
        ArrayList<Nen> nens = new ArrayList<>();
        try (FileReader reader = new FileReader("nens.json")) {
            Type listType = new TypeToken<ArrayList<Nen>>() {}.getType();
            nens = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nens;
    }

    public static ArrayList<Monitor> getMonitors() {
        Gson gson = new Gson();
        ArrayList<Monitor> monitors = new ArrayList<>();
        try (FileReader reader = new FileReader("munis.json")) {
            Type listType = new TypeToken<ArrayList<Monitor>>() {}.getType();
            monitors = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return monitors;
    }

    public static double mitjanaServeisNens(ArrayList<Nen> nens) {
        double sum = 0;
        for (Nen nen : nens) {
            sum += nen.getNumServeis();
        }
        return sum / nens.size();
    }

    private static double mitjanaServeisMunis(ArrayList<Monitor> munis) {
        double sum = 0;
        for (Monitor muni : munis) {
            sum += muni.getNumServeis();
        }
        return sum / munis.size();
    }

    public static void generarServeisNens() {
        List<Servei> serveisNens = new ArrayList<>(List.of(
                new Servei("Parar taula", 6, 2),
                new Servei("Desparar taula", 6, 2),
                new Servei("Plats i peroles", 6, 2),
                new Servei("Interior", 6, 2),
                new Servei("Exterior", 6, 2),
                new Servei("Lavabos", 6, 2),
                new Servei("Pregària", 4, 1)
        ));

        Map<String, List<String[]>> serveisPerDia = new LinkedHashMap<>();

        for (int i = 1; i <= 10; i++) {
            List<String[]> serveisDelDia = new ArrayList<>();
            for (Servei servei : serveisNens) {
                String[] assignacions = new String[servei.getNumNens()];
                int j = 0;
                while (j < servei.getNumNens()) {
                    Nen nen = nens.get((int) (Math.random() * nens.size()));
                    if (nen.getNumServeis() < mitjanaServeisNens(nens) + 0.1) {
                        assignacions[j] = nen.getNom();
                        nen.setNumServeis(nen.getNumServeis() + 1);
                        j++;
                    }
                }
                serveisDelDia.add(assignacions);
            }
            serveisPerDia.put("Dia " + i, serveisDelDia);
        }

        imprimirTaulaNens(serveisPerDia, serveisNens);
    }

    public static void generarServeisMunis() {
        List<Servei> serveisMunis = new ArrayList<>(List.of(
                new Servei("Parar taula", 6, 2),
                new Servei("Desparar taula", 6, 2),
                new Servei("Plats i peroles", 6, 2),
                new Servei("Interior", 6, 2),
                new Servei("Exterior", 6, 2),
                new Servei("Lavabos", 6, 2),
                new Servei("Pregària", 4, 1),
                new Servei("Berenar", 0, 2),
                new Servei("Pànxing", 0, 2),
                new Servei("Imaginària", 0, 3),
                new Servei("Jef@ de Vetllada", 0, 1)
        ));

        Map<String, List<String[]>> serveisPerDia = new LinkedHashMap<>();

        for (int i = 1; i <= 10; i++) {
            List<String[]> serveisDelDia = new ArrayList<>();
            for (Servei servei : serveisMunis) {
                String[] assignacions = new String[servei.getNumMunis()];
                int j = 0;
                while (j < servei.getNumMunis()) {
                    Monitor muni = munis.get((int) (Math.random() * munis.size()));
                    if (muni.getNumServeis() < mitjanaServeisMunis(munis) + 0.1) {
                        assignacions[j] = muni.getNom();
                        muni.setNumServeis(muni.getNumServeis() + 1);
                        j++;
                    }
                }
                serveisDelDia.add(assignacions);
            }
            serveisPerDia.put("Dia " + i, serveisDelDia);
        }

        imprimirTaulaMunis(serveisPerDia, serveisMunis);
    }

    public static void imprimirTaulaMunis(Map<String, List<String[]>> serveisPerDia, List<Servei> serveis) {
        // Capçalera
        System.out.printf("%-35s", "SERVEIS");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%-30s", "| Dia " + i);
        }
        System.out.println();
        System.out.println("-----------------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|");

        for (int i = 0; i < serveis.size(); i++) {
            System.out.printf("%-35s", serveis.get(i).getNom());
            for (int j = 1; j <= 10; j++) {
                List<String[]> assignacionsPerDia = serveisPerDia.get("Dia " + j);
                String[] assignacions = assignacionsPerDia.get(i);
                String noms = String.join(", ", assignacions);
                System.out.printf("%-30s", "| " + noms);
            }
            System.out.println();
            System.out.println("-----------------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|-----------------------------|");
        }
    }

    public static void imprimirTaulaNens(Map<String, List<String[]>> serveisPerDia, List<Servei> serveis) {

        System.out.printf("%-55s", "SERVEIS");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%-130s", "| Dia " + i);
        }
        System.out.println();
        System.out.println("-------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|");

        for (int i = 0; i < serveis.size(); i++) {
            System.out.printf("%-55s", serveis.get(i).getNom());
            for (int j = 1; j <= 10; j++) {
                List<String[]> assignacionsPerDia = serveisPerDia.get("Dia " + j);
                String[] assignacions = assignacionsPerDia.get(i);
                String noms = String.join(", ", assignacions);
                System.out.printf("%-130s", "| " + noms);
            }
            System.out.println();
            System.out.println("-------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|");
        }
    }

}
