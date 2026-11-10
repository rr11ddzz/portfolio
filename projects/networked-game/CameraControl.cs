using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraControl : MonoBehaviour
{
    public Transform playerCharacter;

    Vector3 cameraOffset;

    // Start is called before the first frame update
    void Start()
    {
        cameraOffset = transform.position - playerCharacter.position;
    }

    // Update is called once per frame
    void Update()
    {
        transform.position = playerCharacter.position + cameraOffset;
    }
}
