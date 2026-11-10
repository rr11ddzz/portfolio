using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MenuScene_MainCameraControl : MonoBehaviour
{
    Vector3 cameraOffset;

    void Start()
    {
        cameraOffset = transform.position;
    }

    void Update()
    {
        // Do nothing in the Update method, so the camera stays fixed
        // You can add additional logic here if needed
    }
}

