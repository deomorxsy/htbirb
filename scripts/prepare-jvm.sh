#!/bin/bash
#
distrocheck() {
    if [ -f /etc/os-release ]; then
        distrore=$(cat /etc/os-release | grep "ID=" -m 1)
        elif [[ ${distrore#*=} == "ubuntu" ]]; then
            apt-get update
            apt-get install -y openjdk-17
        elif [[ ${distrore#*=} == "arch" ]]; then
            sudo pacman -Syu --noconfirm archlinux-keyring jdk-openjdk=20.0.2.u9-3
        elif [[ ${distrore#*=} == "fedora" || ${distro#*=} == "rhel" ]]; then
            dnf update
            dnf install java-17-openjdk
        elif [[ ${distrore#*=} == "nixos" ]]; then
            nix-env --upgrade
            nix-env --iA jdk17_headless
        elif [[ ${distrore#*=} == "alpine" ]]; then
            apk -U upgrade
            apk add openjdk-17
        else
            echo "unsupported distro"
        fi
    else
        echo "unknown distro"


}
