public class MersenneTwister
{
    public static MTRandom gen = new MTRandom(new int[]{0x123, 0x234, 0x345, 0x456});

    /**
     * Génère un entier aléatoire
     */
    public static long genrand_int32 ()
    {
        return Integer.toUnsignedLong(gen.next(32));
    }

    /**
     * Génère un double dans l'intervalle [0,1] (Repris du code originel de Makoto)
     *
     * @return      Un nombre flottant double précision dans l'intervalle [0,1]
     */
    public static double genrand_real1 ()
    {
        return genrand_int32() * (1.0/4294967295.0);
    }

    /**
     * Génère un nombre flottant double précision aléatoire dans l'intervalle [a,b[
     *
     * @param   a   La valeur minimum (comprise)
     * @param   b   La valeur maximum (non comprise)
     *
     * @return      Un nombre flottant double précision dans l'intervalle [a,b[
     */
    public static double uniform (double a, double b)
    {
        return a + (b - a) * genrand_real1();
    }
}
