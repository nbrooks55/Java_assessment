package com.componentwise.eval;
import java.io.*;
/** Represents a UserKey that is serializable
 * Date: June 2nd 2021
 * @author Nicholas Brooks
 * @version 1.7
*/
public class UserKey implements Serializable{

	private String name;
	private String userid;


	 /** Creates an user with the specified name and id.
	  * @param name The users’s name.
	  * @param userid The users’s id.
	 */
	public UserKey(String name, String userid) {
		this.name = name;
		this.userid = userid;
	}

	 /** Returns user's name.
	  * @return A String representing the user's name.
	 */
	public String getName() {
		return name;
	}

	 /** Returns user's id.
	  * @return A String representing the user's name.
	 */
	public String getUserID() {
		return userid;
	}

	 /** Compares user with a object of generic type.
	  * @param obj A generic object the UserKey is being compared to.
	  * @return returns true if UserKey has same name and userID.
	 */
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final UserKey other = (UserKey) obj;
        if(this.name == null || other.name == null) {
        	return false;
        }
        if(this.userid == null || other.userid == null) {
        	return false;
        }
        if (!this.name.equals(other.name)) {
            return false;
        }

        if (!this.userid.equals(other.userid)) {
            return false;
        }

        return true;
    }
}
