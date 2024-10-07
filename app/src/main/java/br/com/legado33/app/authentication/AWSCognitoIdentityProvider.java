// package br.com.legado33.app.authentication;
// import software.amazon.awssdk.services.cognitoidentity;

// private AWSCognitoIdentityProvider createCognitoClient() {
//     AWSCredentials cred = new BasicAWSCredentials(access_key, secret_key);
//     AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
//     return AWSCognitoIdentityProviderClientBuilder.standard()
//             .withCredentials(credProvider)
//             .withRegion(region)
//             .build();
// }