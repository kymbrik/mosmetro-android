package pw.thedrhax.mosmetro.authenticator;

import android.content.Context;
import android.net.wifi.WifiManager;
import pw.thedrhax.util.Logger;

public class Chooser {
    private Context context;
    private boolean automatic;
    private Logger logger;

    public Chooser(Context context, boolean automatic, Logger logger) {
        this.logger = logger;
        this.context = context;
        this.automatic = automatic;
    }

    public Authenticator choose (String SSID) {
        logger.log_debug(">> Поиск подходящего алгоритма для сети " + SSID);

        // Trying to match one of Authenticators for this SSID
        Class<? extends Authenticator> result_class = null;
        for (Class<? extends Authenticator> network : Authenticator.SUPPORTED_NETWORKS) {
            try {
                String class_ssid = (String) network.getField("SSID").get(network);
                if ((SSID.equals(class_ssid))) {
                    result_class = network; break;
                }
            } catch (Exception ignored) {}
        }

        if (result_class == null) {
            logger.log_debug("<< Ошибка: Вы не подключены ни к одной из поддерживаемых сетей");
            return null;
        }

        // Make instance of matched Authenticator
        Authenticator result;
        try {
            result = result_class
                    .getConstructor(Context.class, boolean.class)
                    .newInstance(context, automatic);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return result;
    }

    public Authenticator choose() {
        // Get SSID from WifiManager
        logger.log_debug(">> Определение названия сети");
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String SSID = manager.getConnectionInfo().getSSID();

        return choose(SSID);
    }
}