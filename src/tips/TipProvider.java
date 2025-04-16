
package tips;

import user.UserProfile;

public interface TipProvider {
    String getHealthTip();
    String getHealthTipForUser(UserProfile userProfile);
}