using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MultiplayerCameraControl : MonoBehaviour
{
    private Transform playerCharacter;
    private Vector3 cameraOffset;
    private float movementSpeed = 10f;

    public void WakeUpCamera(GameObject player)
    {
        playerCharacter = player.transform;

        if (playerCharacter == null)
        {
            Debug.LogError("Player character not found. Make sure to tag your player GameObject as 'Player'.");
        }

        cameraOffset = transform.position - playerCharacter.position;
    }

    void Update()
    {
        if (playerCharacter != null)
        {
            MoveCamera();
        }
        else
        {
            FindPlayerCharacter();
        }
    }

    void FindPlayerCharacter()
    {
        GameObject player = GameObject.FindGameObjectWithTag("Player");
        if (player != null)
        {
            playerCharacter = player.transform;
            cameraOffset = transform.position - playerCharacter.position;
        }
        else
        {
            Debug.LogWarning("Player character not found. Camera movements disabled.");
        }
    }

    void MoveCamera()
    {
        transform.position = playerCharacter.position + cameraOffset;

    }
    public void DisconnectPlayer()
    {
        playerCharacter = null;
    }

}




