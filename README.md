## htbirb

A proof-of-concept bsd socket server from scratch in java implementing at least 3 http status response codes.

### 1. clone-build-run

1. clone repository with SSH and enter directory
```sh
git clone git@github.com:deomorxsy/htbirb.git
cd ./htbirb/
```

2. build and run with maven, a package manager and build tool
```sh
mvn compile exec:java -Dexec.mainClass="com.htbirb"

```

### 2. run a container

> PS.1: the code below assumes you are running a localhost oci image registry. If you don't, just create one with the code below or push it to a remote registry. Then check if  registry is running with ```podman images``` or similar. Run with ```podman start registry```.
```sh
podman run -d --name registry -p 5000:5000 -v /var/lib/registry:/var/lib/registry --restart=always registry:2
```

> PS.2: the flag ```--restart``` is usually related with privileged contexts, and this is rootless. If it don't start after a reboot, just start manually with podman-start.

Now, you can also build, tag, push and run an oci image with any high-level container manager (docker, podman, containerd, etc) with the steps below. Be sure at the last step to expose the port, or you won't be able to see the server.
```sh
podman build -t htbirb_test:v01 -f ./Dockerfile
podman tag htbirb_test:v01  localhost:5000/htbirb_test:v01
podman push localhost:5000/htbirb_test:01
podman run --rm -it --entrypoint=/bin/sh -p=5000:5000 localhost:5000/htbirb_test:v01
```

