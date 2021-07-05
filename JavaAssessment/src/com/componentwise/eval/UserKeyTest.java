package com.componentwise.eval;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

public class UserKeyTest {

	@Test 
	public void testName() {
		UserKey newUser = new UserKey("Jeffrey", "1234");
		Assert.assertEquals(newUser.getName(), "Jeffrey");
		
	}
	@Test
	public void testUserID() {
		UserKey newUser = new UserKey("Bezos", "1234");
		Assert.assertEquals(newUser.getUserID(), "1234");
		
	}
	@Test
	public void testEquals() {
		UserKey newUser = new UserKey("Dave", "1234");
		UserKey newUserB = new UserKey("Dave", "1234");
		UserKey newUserC = new UserKey("Bill", "1234");
		Assert.assertTrue(newUser.equals(newUserB));
		Assert.assertFalse(newUser.equals(newUserC));
		
	}
	@Test
	public void testSerializable() throws IOException, CloneNotSupportedException, ClassNotFoundException  {
		UserKey newUser = new UserKey("Jeff", "1234");
		byte[] serialized1 = serialize(newUser);
        byte[] serialized2 = serialize(newUser);
 
        Object deserialized1 = deserialize(serialized1);
        Object deserialized2 = deserialize(serialized2);
        Assert.assertEquals(deserialized1, deserialized2);
        Assert.assertEquals(newUser, deserialized1);
        Assert.assertEquals(newUser, deserialized2);
		
	}
    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
 
    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}
