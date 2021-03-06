/**
 * Wi-Fi в метро (pw.thedrhax.mosmetro, Moscow Wi-Fi autologin)
 * Copyright © 2015 Dmitry Karikh <the.dr.hax@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pw.thedrhax.util;

import android.support.annotation.NonNull;

import pw.thedrhax.mosmetro.BuildConfig;

/**
 * The Version util class is used to get formatted application version
 * information from build-time constants.
 *
 * @author Dmitry Karikh <the.dr.hax@gmail.com>
 * @author Savelii Zagurskii <saveliyzagurskiy@gmail.com>
 */
public final class Version {
    private Version() {}

    @NonNull public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    @NonNull public static String getFormattedVersion() {
        return getVersionName() + "-" + getVersionCode();
    }

    // TODO: Store branch name in app.gradle
    @NonNull public static String getBranch() {
        String version_name = Version.getVersionName();

        if (version_name.contains("#")) {
            return version_name.substring(0, version_name.indexOf("#") - 1);
        } else {
            return "play";
        }
    }

    // TODO: Store build number in app.gradle
    public static int getBuildNumber() {
        String version_name = Version.getVersionName();

        if (version_name.contains("#")) {
            return Integer.parseInt(version_name.substring(version_name.indexOf("#") + 1));
        } else {
            return 0; // play or beta are currently installed
        }
    }
}
