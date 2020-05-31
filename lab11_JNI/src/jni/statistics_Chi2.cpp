#include <jni.h>
#include <iostream>
#include <string.h>
#include "statistics_Chi2.h"

using namespace std;

JNIEXPORT statistics_Chi2_calculate0(JNIEnv *env, jobject thisObj, jobjectArray observedArr, jobjectArray expectedArr){

	int lengthObs = env->GetArrayLength(observedArr);
    int lengthExp = env->GetArrayLength(expectedArr);
    jobject arrObsObj = env->GetObjectArrayElement(observedArr, false);
    jobject arrExpObj = env->GetObjectArrayElement(expectedArr, false);
	jclass doubleClass = env->GetObjectClass(arrObsObj);
	jmethodID doubleValue = env->GetMethodID(doubleClass, "doubleValue", "()D");
	jobjectArray result = env->NewObjectArray(lengthObs, doubleClass, NULL);
	//niepotrzebne
	//jclass doubleClassExp = env->GetObjectClass(arrExpObj);
	//jmethodID doubleValueExp = env->GetMethodID(doubleClassExp, "doubleValue", "()D");
	//jobjectArray resultExp = env->NewObjectArray(lengthExp, doubleClassExp, NULL);

	if (doubleValue == NULL)
		return NULL;

	double *obsArr = new double[lengthObs];
	double *expArr = new double[lengthExp];

	for (int i = 0; i < lengthObs; ++i) {
		arrObsObj = env->GetObjectArrayElement(observedArr, i);
		obsArr[i] = env->CallDoubleMethod(arrObsObj, doubleValue);

		arrExpObj = env->GetObjectArrayElement(expectedArr, i);
		expArr[i] = env->CallDoubleMethod(arrExpObj, doubleValue);
	}

	jmethodID init = env->GetMethodID(doubleClass, "<init>", "(D)V");

	for (int i = 0; i < length; i++) {
		double tempResult = (pow((obsArr[i] - expArr[i]), 2) / expArr[i]);
		env->SetObjectArrayElement(result, i, env->NewObject(doubleClass, constructor, tempResult));
	}

	delete observedArr, expectedArr;
	env->DeleteLocalRef(doubleClass);
	env->DeleteLocalRef(arrObsObj);
	env->DeleteLocalRef(arrExpObj);
	delete[] obsArr;
	delete[] expArr;

	return result;

}
JNIEXPORT statistics_Chi2_calculate(JNIEnv *env, jobject thisObj, jobjectArray observedArr) {

	int lengthObs = env->GetArrayLength(observedArr);;
	jobject arrObsObj = env->GetObjectArrayElement(observedArr, false);
	jclass doubleClass = env->GetObjectClass(arrObsObj);
	jmethodID doubleValue = env->GetMethodID(doubleClass, "doubleValue", "()D");
	jobjectArray result = env->NewObjectArray(lengthObs, doubleClass, NULL);

	if (doubleValue == NULL)
		return NULL;

	double *obsArr = new double[lengthObs];
	double *expArr = new double[lengthObs];

	jclass doubleClassExp= env->GetObjectClass(thisObj);
	jfieldID field = env->GetFieldID(doubleClassExp, "expected", "[Ljava/lang/Double;");
	jobjectArray expectedArr = (jobjectArray)env->GetObjectField(thisObj, field);
	jobject arrExpObj = env->GetObjectArrayElement(expectedArr, false);

	for (int i = 0; i < lengthObs; i++) {
		arrExpObj = env->GetObjectArrayElement(expectedArr, i);
		expArr[i] = env->CallDoubleMethod(arrExpObj, doubleValue);
		arrObsObj = env->GetObjectArrayElement(observedArr, i);
		obsArr[i] = env->CallDoubleMethod(arrObsObj, doubleValue);
	}

	cout << "Result: ";
	for (int i = 0; i < length; i++) {
		double tempResult = (pow((obsArr[i] - expArr[i]), 2) / arrExp[i]);
		cout << tempResult << ", ";
	}

	delete observedArr;
	env->DeleteLocalRef(doubleClass);
	env->DeleteLocalRef(arrObsObj);
	env->DeleteLocalRef(arrExpObj);
	delete[] obsArr;
	delete[] expArr;
}

JNIEXPORT statistics_Chi2_calculate(JNIEnv *env, jobject thisObj) {
	int n;
	cout << "Number of cases: ";
	cin >> n;

	jclass jc = env->GetObjectClass(thisObj);
	jfieldID fieldObs = env->GetFieldID(jc, "observed", "[Ljava/lang/Double;");
	jfieldID fieldExp = env->GetFieldID(jc, "expected", "[Ljava/lang/Double;");

	jclass doubleClass = env->FindClass("java/lang/Double");

	jobjectArray result = env->NewObjectArray(n, doubleClass, NULL);

	double *obsArr = new double[n];
	double *expArr = new double[n];
	double tmp;

	cout << "Give observed values (use enter after every number): " << endl;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		obsArr[i] = tmp;
	}

	cout << "Give expected values (use enter after every number): " << endl;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		expArr[i] = tmp;
	}


	jmethodID initD = env->GetMethodID(doubleClass, "<init>", "(D)V");

	for (int i = 0; i < n; i++) {
		double tempResult = (pow((obsArr[i] - expArr[i]), 2) / expArr[i]);
		env->SetObjectArrayElement(result, i, env->NewObject(doubleClass, constructor, tempResult));
	}

	env->DeleteLocalRef(doubleClass);
	delete[] obsArr;
	delete[] expArr;

	return result;
	
}