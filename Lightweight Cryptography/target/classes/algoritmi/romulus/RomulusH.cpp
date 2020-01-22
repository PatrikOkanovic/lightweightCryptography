#include "algoritmi_romulus_RomulusH.h"
#include "crypto_aead.h"
#include <malloc.h>

#define CRYPTO_ABYTES 16            //iz api.h
#define MAX_MESSAGE_LENGTH 1024       //iz genkat_aead.c

//gore navedene brojeve cemo kasnije promijeniti da budu veci, za sada je ovo dovoljno

extern "C"{
    JNIEXPORT jbyteArray JNICALL Java_algoritmi_romulus_RomulusH_encrypt
    (JNIEnv* env, jobject thisObject, jbyteArray message, 
    jlong mLength, jbyteArray aData, jlong aDataLength, 
    jbyteArray nonce, jbyteArray key){

        jboolean isCopy;

        unsigned char* m = (unsigned char*) env->GetByteArrayElements(message, &isCopy);
        unsigned long long mlen = (unsigned long long) mLength;

        unsigned char* ad = (unsigned char*) env->GetByteArrayElements(aData, &isCopy);
        unsigned long long adlen = (unsigned long long) aDataLength;

        unsigned char* npub = (unsigned char*) env->GetByteArrayElements(nonce, &isCopy);

        unsigned char* k = (unsigned char*) env->GetByteArrayElements(key, &isCopy);

        unsigned char* c;
        c = (unsigned char*) malloc ((MAX_MESSAGE_LENGTH + CRYPTO_ABYTES) * sizeof (char));
        unsigned long long clen = 0;

        int unsuccessful = crypto_aead_encrypt(c, &clen, m, mlen, ad, adlen, NULL, npub, k);

        jbyteArray crypted = (*env).NewByteArray(clen);
        (*env).SetByteArrayRegion(crypted, 0, clen, (jbyte*) c);
        free(c);

        return crypted;
    }
    
    JNIEXPORT jbyteArray JNICALL Java_algoritmi_romulus_RomulusH_decrypt
    (JNIEnv* env, jobject thisObject, jbyteArray crypted, 
    jlong cLength, jbyteArray aData, jlong aDataLength, 
    jbyteArray nonce, jbyteArray key){
            
        jboolean isCopy;

        unsigned char* c = (unsigned char*) env->GetByteArrayElements(crypted, &isCopy);
        unsigned long long clen = (unsigned long long) cLength;

        unsigned char* ad = (unsigned char*) env->GetByteArrayElements(aData, &isCopy);
        unsigned long long adlen = (unsigned long long) aDataLength;

        unsigned char* npub = (unsigned char*) env->GetByteArrayElements(nonce, &isCopy);

        unsigned char* k = (unsigned char*) env->GetByteArrayElements(key, &isCopy);

        unsigned char* m;
        m = (unsigned char*) malloc ((MAX_MESSAGE_LENGTH + CRYPTO_ABYTES) * sizeof (char));
        unsigned long long mlen = 0;

        int unsuccessful = crypto_aead_decrypt(m, &mlen, NULL, c, clen, ad, adlen, npub, k);

        jbyteArray decrypted = (*env).NewByteArray(mlen);
        (*env).SetByteArrayRegion(decrypted, 0, mlen, (jbyte*) m);
        free(m);

        return decrypted;
    }
}