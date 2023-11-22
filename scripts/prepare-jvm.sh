#!/bin/bash
#
distrocheck() {
    if [ -f /etc/os-release ]; then
        distro=$(cat /etc/os-release | grep "ID=" -m 1)

        elif [[ ${distro#*=} == "ubuntu" ]]; then
            apt-get update
            apt-get install openjdk-17
        elif [[ ${distro#*=} == "arch" ]]; then
            sudo pacman -Syu archlinux-keyring jdk-openjdk=20.0.2.u9-3
        elif [[ ${distro#*=} == "fedora" || ${distro#*=} == "rhel"]]; then
            yum update
            yum install
        elif [[ ${distro#*=} == "nixos" ]]; then
            programs.neovim.
        elif [[ ${distro#*=} == "alpine" ]]; then
            apk -U upgrade
            apk add openjdk-17
        else
            echo "unsupported distro"
        fi
    else
        echo "unknown distro"


}
