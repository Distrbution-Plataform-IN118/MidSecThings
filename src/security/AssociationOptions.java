package security;

public interface AssociationOptions {
	int NO_PROTECTION = 0;
	int INTEGRITY = 2;
	int CONFIDENTIALITY = 4;
	int DETECTREPLAY = 8;
	int DETECTMISORDERING = 16;
	int ESTABLISHTRUSTINTARGET = 32;
	int ESTABLISHTRUSTINCLIENT = 64;
	int NODELEGATION = 128;
	int SIMPLEDELEGATION = 256;
	int COMPOSITEDELEGATION = 512;
	
}
