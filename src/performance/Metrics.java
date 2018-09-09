package performance;

public interface Metrics {
	public final static int SAMPLE = 10000;
	public final static  long [] INVOKER_DURATION_LIST = new long [SAMPLE];
	public final static  long [] UNMARSHALL_CLIENT_DURATION_LIST = new long [SAMPLE];
	public final static  long [] RTT_CLIENT_DURATION_LIST = new long [SAMPLE];
	public final static String output_sample_file = "C:\\Users\\Richardson\\Documents\\";
}
