import java.lang.Math;

public class Main
{

    public static void main ( String[] args )
    {
        int     i, j,               // Pour les boucles
                sum = 0,            // Somme des résultats d'expérience
                nbExp = 100,        // Nombre d'expériences indépendantes
                dureeSim = 60;      // Durée en mois d'une simulation

        int[]   result = new int[nbExp];

        for (j = 0; j < nbExp; j++)
        {
            System.out.println("\nDébut de la simulation " + (j + 1) + " ...");

            Rabbit  lapin1 = new RabbitMale(12, 5),
                    lapin2 = new RabbitFemale(12, 5);

            Population pop = new Population(lapin1, lapin2);

            System.out.println("---------- État initial ----------");

            System.out.println("Population : " + pop.nbRabbit() + " lapins.");

            for (i = 0; i < dureeSim; i++)
            {
                System.out.println("---------- Mois " + (i + 1) + " ----------");

                // Simulation du mois
                pop.newMonth();

                // Affichage de la population après simulation
                System.out.println("Population à la fin du mois : " + pop.nbRabbit() + " lapins.");

                // S'il n'y a plu de mâle, la simulation s'arrête
                if (! pop.hasGender(Rabbit.Gender.MALE))
                {
                    System.out.println("Plus aucun lapin mâle dans la population.");
                    j--; // Si la simulation ne va pas jusqu'au bout, elle est considérée comme nulle et n'est pas prise en compte.
                    break;
                }

                // S'il n'y a plu de femelle, la simulation s'arrête
                if (! pop.hasGender(Rabbit.Gender.FEMALE))
                {
                    System.out.println("Plus aucun lapin femelle dans la population.");
                    j--; // Si la simulation ne va pas jusqu'au bout, elle est considérée comme nulle et n'est pas prise en compte.
                    break;
                }

                // À la fin de la simulation...
                if ((i + 1) == dureeSim)
                {
                    result[j] = pop.nbRabbit();
                    sum += result[j];
                }
            }
        }


        // Calcul de la moyenne des expériences
        double  mean = (double) sum / nbExp;


        // Calcul intervalle de confiance
        double  topS = 0,
                S;

        // S
        for (i = 0; i < nbExp; i++)
        {
            topS += Math.pow((result[i] - mean), 2);
        }

        S = (topS / (nbExp - 1));

        // Choix de t
        double      t;
        double[]    tValues =  {12.706, 4.303, 3.182, 2.776, 2.571,
                                2.447, 2.365, 2.308, 2.262, 2.228,
                                2.201, 2.179, 2.160, 2.145, 2.131,
                                2.120, 2.110, 2.101, 2.093, 2.086,
                                2.080, 2.074, 2.069, 2.064, 2.060,
                                2.056, 2.052, 2.048, 2.045, 2.042};

        if (nbExp <= 30)
        {
            t = tValues[nbExp - 1];
        }
        else if (nbExp == 40)
        {
            t = 2.021;
        }
        else if (nbExp == 80)
        {
            t = 2.0;
        }
        else if (nbExp == 120)
        {
            t = 1.980;
        }
        else
        {
            t = 1.960;
        }

        // Calcul de R
        double R = t * Math.sqrt(S / nbExp);

        // Calcul de l'erreur type
        double standardError = (S / Math.sqrt(nbExp));


        System.out.print("\nStatistiques :\n");
        System.out.printf("Nombre d'expériences : %d\n", nbExp);
        System.out.printf("Durée d'une simulation : %d mois\n", dureeSim);
        System.out.printf("Moyenne des estimations : %.3f\n", mean);
        System.out.printf("Estimation de la variance = %.3f\n", S);
        System.out.printf("Marge d'erreur = %.3f\n", R);
        System.out.printf("L'intervalle de confiance est [%.3f, %.3f]\n", (mean - R) , (mean + R));
        System.out.printf("Erreur type = %.3f\n", standardError);
    }
}
