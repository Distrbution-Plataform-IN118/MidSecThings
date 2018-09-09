package security;

public enum DelegationMode {
	SecDelModeNoDelegation, // i.e. use own credentials
	SecDelModeSimpleDelegation, // delegate received credentials
	SecDelModeCompositeDelegation // delegate both;
}
