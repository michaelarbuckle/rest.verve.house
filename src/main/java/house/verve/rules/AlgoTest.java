package house.verve.rules;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class AlgoTest {

	public static void main(String[] args)
	{
		AlgoTest AlgoTest = new AlgoTest();
		float[] in = {0f,0f,8f,0f,0f};
		 float[] out = AlgoTest.integrate(in, 2f);
		 StringBuilder sb = new StringBuilder();
		 sb.append("[");
		 for (float f:out)
		 {
			 sb.append(f).append(",");
		 }
		System.out.println(sb.toString());
	}
	float[] integrate(float[] u_0, float T) {
	    float[] u = u_0;
	    float t = 0;
	    float dt = 1.0f;
	    float D = .25f;
	    float dx = 1.0f;
	    while (t < T) {
	        float[] new_u = duplicate(u);
	        for (int i=1; i<u.length-1; i++) {
	            float du_dt = D * (u[i+1] + u[i-1] - 2*u[i]) / (dx*dx);
	            new_u[i] += du_dt * dt;
	        }
	        for (int i=1; i<u.length-1; i++) {
	            u[i] = new_u[i];
	        }
	        t += dt;
	    }
	    return u;
	}
	float[] duplicate(float[] array)
	{
		float[] d = Arrays.copyOf(array, array.length);
		return d;
	}
}
